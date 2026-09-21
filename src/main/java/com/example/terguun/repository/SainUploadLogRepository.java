package com.example.terguun.repository;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.terguun.model.SainUploadLog;

public interface SainUploadLogRepository extends JpaRepository<SainUploadLog, Long> {

    /** Жагсаалтаас хасах шүүлтэд: тухайн данснуудын амжилттай илгээлтүүдийг нэг асуулгаар татна. */
    List<SainUploadLog> findBySuccessTrueAndAccountIdIn(Collection<String> accountIds);

    List<SainUploadLog> findTop200ByOrderByUploadedOnDesc();

    /** "Илгээлтийн лог" хуудсанд: [from, to) хугацааны илгээлтүүд, шинэ нь эхэндээ. */
    List<SainUploadLog> findByUploadedOnGreaterThanEqualAndUploadedOnLessThanOrderByUploadedOnDesc(
            LocalDateTime from, LocalDateTime to);
}
