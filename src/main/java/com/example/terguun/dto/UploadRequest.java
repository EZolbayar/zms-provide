package com.example.terguun.dto;
import java.util.List;

import com.example.terguun.dto.sain.CustomerData;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class UploadRequest {

    private List<CustomerData> data;

}
