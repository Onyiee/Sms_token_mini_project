package com.miniProject.smsToken.data.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class UserDto {
    private String id;
    private String firstName;
    private String lastName;
    @NotBlank(message = "phone number cannot be blank")
    private String phoneNumber;
    private boolean isVerified;
}
