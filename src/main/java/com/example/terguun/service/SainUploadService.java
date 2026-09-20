package com.example.terguun.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.example.terguun.client.SainServiceClient;
import com.example.terguun.dto.SainUploadPayload;
import com.example.terguun.dto.sain.CitizenUploadRequest;
import com.example.terguun.dto.sain.CitizenUploadResponse;
import com.example.terguun.dto.sain.CustomerData;
import com.example.terguun.dto.sain.EntityData;
import com.example.terguun.dto.sain.EntityUploadRequest;
import com.example.terguun.model.SainUploadLog;
import com.example.terguun.repository.SainUploadLogRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import tools.jackson.databind.DeserializationFeature;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;
import tools.jackson.databind.json.JsonMapper;

/**
 * ЗМС рүү (Сайн системээр дамжин) мэдээлэл илгээх нэгдсэн үйлчилгээ. Иргэнийг /upload-citizen,
 * хуулийн этгээдийг /upload-entity рүү илгээх бөгөөд хоёулаа ижил бүрхүүл (patch_number,
 * data_provider_*) болон ижил хариу бүтэцтэй тул энд нэг дор хийв.
 */
@Log4j2
@Service
@RequiredArgsConstructor
public class SainUploadService {

    /**
     * Протокол: patch_number нь мэдээлэл нийлүүлэгчийн хувьд дахин давтагдахгүй 10-50 оронтой тоо байна.
     * Нэг батчийн доторх хүсэлтүүд ижил миллисекундэд үүсвэл давхардаж DBE1036 алдаа өгдөг тул
     * цагийн тэмдэг дээр өсөн нэмэгдэх дугаар залгаж 18 оронтой болгоно.
     */
    private static final AtomicLong PATCH_SEQUENCE = new AtomicLong(System.currentTimeMillis() * 100000L);

    private final SainServiceClient sainServiceClient;
    private final SainUploadLogRepository uploadLogRepository;

    private final ObjectMapper sainResponseMapper = JsonMapper.builder()
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
            .build();

    @Value("${sain.upload.citizen}")
    private String citizenPath;

    @Value("${sain.upload.entity}")
    private String entityPath;

    @Value("${data.provider.regnum}")
    private String dataProviderRegnum;

    @Value("${data.provider.branch}")
    private String dataProviderBranch;

    /** Зөвхөн иргэний мэдээлэл илгээх (POST /api/upload-citizen). */
    public List<CitizenUploadResponse> uploadCitizenData(List<CustomerData> requests) {
        return upload(requests.stream().map(SainUploadPayload::ofCitizen).toList(), null);
    }

    /**
     * Холимог жагсаалтыг төрлөөр нь зөв сувагт илгээнэ. Хариунууд нь оролтын дарааллатай яг таарч
     * буцна — дуудагч тал үр дүнг индексээр нь мөртэй нь холбодог.
     *
     * Мөр бүрийн үр дүнг TBSAINUPLOADLOG-д бүртгэнэ. Амжилттай бүртгэл нь тухайн мөрийг "Нийлүүлэх
     * мэдээлэл" жагсаалтаас хасах үндэслэл болно.
     */
    public List<CitizenUploadResponse> upload(List<SainUploadPayload> payloads, String uploadedBy) {
        log.info("ЗМС upload эхэллээ. Нийт={}, хуулийн этгээд={}", payloads.size(),
                payloads.stream().filter(SainUploadPayload::isEntity).count());

        List<CitizenUploadResponse> responses = new ArrayList<>(payloads.size());
        List<SainUploadLog> logs = new ArrayList<>(payloads.size());
        int failureCount = 0;

        for (SainUploadPayload payload : payloads) {
            String patchNumber = String.valueOf(PATCH_SEQUENCE.incrementAndGet());
            String rawBody = null;
            CitizenUploadResponse response;
            try {
                rawBody = payload.isEntity()
                        ? sainServiceClient.uploadEntity(entityRequest(patchNumber, payload.entity()))
                        : sainServiceClient.uploadCitizen(citizenRequest(patchNumber, payload.citizen()));
                response = parseResponse(rawBody);
            } catch (Exception ex) {
                log.error("ЗМС upload амжилтгүй. patchNumber={}, entity={}, body={}, error={}",
                        patchNumber, payload.isEntity(), rawBody, ex.getMessage(), ex);
                // Мөр бүрд нэг хариу нэмснээр дуудагч тал индексээр нь тааруулж чадна.
                response = CitizenUploadResponse.builder()
                        .success(false)
                        .errors(new String[] { ex.getMessage() })
                        .build();
            }

            boolean success = Boolean.TRUE.equals(response.getSuccess());
            if (!success) {
                failureCount++;
            }
            responses.add(response);
            logs.add(toLog(payload, patchNumber, response, success, uploadedBy));
        }

        uploadLogRepository.saveAll(logs);
        log.info("ЗМС upload дууслаа. Нийт={}, амжилтгүй={}, бүртгэл={}",
                payloads.size(), failureCount, logs.size());
        return responses;
    }

    private SainUploadLog toLog(SainUploadPayload payload, String patchNumber,
            CitizenUploadResponse response, boolean success, String uploadedBy) {
        String errors = response.getErrors() == null ? null : String.join("; ", response.getErrors());
        return SainUploadLog.builder()
                .clientId(payload.clientId())
                .accountId(payload.accountId())
                .changeType(payload.changeType())
                .customerType(payload.customerType())
                .endpoint(payload.isEntity() ? entityPath : citizenPath)
                .patchNumber(patchNumber)
                .success(success)
                .errorMessage(errors == null || errors.length() <= 2000 ? errors : errors.substring(0, 2000))
                .accountModifiedOn(payload.accountModifiedOn())
                .uploadedOn(LocalDateTime.now())
                .uploadedBy(uploadedBy)
                .build();
    }

    private CitizenUploadRequest citizenRequest(String patchNumber, CustomerData customer) {
        return CitizenUploadRequest.builder()
                .patchNumber(patchNumber)
                .dataProviderRegnum(dataProviderRegnum)
                .dataProviderBranch(dataProviderBranch)
                .customerData(List.of(customer))
                .build();
    }

    private EntityUploadRequest entityRequest(String patchNumber, EntityData entity) {
        return EntityUploadRequest.builder()
                .patchNumber(patchNumber)
                .dataProviderRegnum(dataProviderRegnum)
                .dataProviderBranch(dataProviderBranch)
                .customerData(List.of(entity))
                .build();
    }

    /** Sain нь ганц объект эсвэл нэг элементтэй массив буцаадаг тул хоёуланг нь хүлээж авна. */
    private CitizenUploadResponse parseResponse(String rawBody) {
        if (rawBody == null || rawBody.isBlank()) {
            return CitizenUploadResponse.builder().success(true).build();
        }
        JsonNode node = sainResponseMapper.readTree(rawBody);
        if (node.isArray()) {
            node = node.isEmpty() ? null : node.get(0);
        }
        if (node == null || node.isNull()) {
            return CitizenUploadResponse.builder().success(true).build();
        }
        return sainResponseMapper.treeToValue(node, CitizenUploadResponse.class);
    }
}
