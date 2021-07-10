package com.miniProject.smsToken.web.service;

import com.miniProject.smsToken.data.model.User;
import com.miniProject.smsToken.exceptions.TokenException;
import org.springframework.stereotype.Service;

@Service
public interface TokenSender {
    void sendToken(User user) throws TokenException;
}
