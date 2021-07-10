package com.miniProject.smsToken;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SmsTokenConfig {
    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }
}
