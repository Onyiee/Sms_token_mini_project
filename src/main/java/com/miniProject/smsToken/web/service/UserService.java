package com.miniProject.smsToken.web.service;

import com.miniProject.smsToken.data.dto.ResponseDto;
import com.miniProject.smsToken.data.dto.UserDto;
import com.miniProject.smsToken.data.model.User;
import com.miniProject.smsToken.exceptions.TokenException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    List<UserDto> findAll();
    ResponseDto registerUser(UserDto userDto) throws TokenException;

}
