package com.miniProject.smsToken.data.dto;

import lombok.Data;

@Data
public class ResponseDto {
    private String userId;
    private String verificationCode;
}
