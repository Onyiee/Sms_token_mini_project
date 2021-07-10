package com.miniProject.smsToken.web.service;

import com.miniProject.smsToken.data.model.TwilioConfiguration;
import com.miniProject.smsToken.data.model.User;
import com.miniProject.smsToken.exceptions.TokenException;
import com.twilio.exception.ApiException;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.rest.api.v2010.account.MessageCreator;
import com.twilio.type.PhoneNumber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

@Service
public class TokenSenderImpl implements TokenSender{
    private final TwilioConfiguration twilioConfiguration;
    private static  final Logger LOGGER = LoggerFactory.getLogger(TokenSenderImpl.class);

    @Autowired
    public TokenSenderImpl(TwilioConfiguration twilioConfiguration) {
        this.twilioConfiguration = twilioConfiguration;
    }


    @Override
    public void sendToken(User user) throws TokenException {
        try {
            if (isPhoneNumberValid(user.getPhoneNumber())) {
                PhoneNumber to = new PhoneNumber(user.getPhoneNumber());
                PhoneNumber from = new PhoneNumber(twilioConfiguration.getTrialNumber());
                String verificationCode = user.getVerificationCode();
                MessageCreator creator = Message.creator(to, from, verificationCode);
                creator.create();
                LOGGER.info("Send code {} to {}", verificationCode, user.getPhoneNumber());
            } else {
                throw new TokenException(
                        "Phone number [" + user.getPhoneNumber() + "] is not a valid number"
                );
            }
        } catch (ApiException apiException) {
            throw new TokenException(apiException.getMessage());
        }
    }

    private boolean isPhoneNumberValid(String phoneNumber) {
        return true;
    }
}
