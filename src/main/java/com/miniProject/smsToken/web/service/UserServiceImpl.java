package com.miniProject.smsToken.web.service;

import com.miniProject.smsToken.data.dto.ResponseDto;
import com.miniProject.smsToken.data.dto.UserDto;
import com.miniProject.smsToken.data.model.TwilioConfiguration;
import com.miniProject.smsToken.data.model.User;
import com.miniProject.smsToken.data.repository.UserRepository;
import com.miniProject.smsToken.exceptions.TokenException;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.rest.api.v2010.account.MessageCreator;
import com.twilio.type.PhoneNumber;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    @Lazy
    UserRepository userRepository;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    private TokenSender tokenSender;

    @Override
    public List<UserDto> findAll() {
        List<User> users = userRepository.findAll();
        List<UserDto> userDtos = users.stream()
                .map(user -> modelMapper.map(
                        user, UserDto.class
                )).collect(Collectors.toList());
        return userDtos;
    }

    @Override
    public ResponseDto registerUser(UserDto userDto) throws TokenException {
        User newUser = modelMapper.map(userDto, User.class);
        String verificationCode = UUID.randomUUID().toString();
        newUser.setVerificationCode(verificationCode);
        tokenSender.sendToken(newUser);
        User savedUser = userRepository.save(newUser);
        ResponseDto response = new ResponseDto();
        response.setUserId(savedUser.getId());
        response.setVerificationCode(verificationCode);
        return response;
    }


}
