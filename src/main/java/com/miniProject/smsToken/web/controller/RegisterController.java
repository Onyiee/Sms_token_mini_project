package com.miniProject.smsToken.web.controller;

import com.miniProject.smsToken.data.dto.ApiResponse;
import com.miniProject.smsToken.data.dto.ResponseDto;
import com.miniProject.smsToken.data.dto.UserDto;
import com.miniProject.smsToken.data.model.User;
import com.miniProject.smsToken.exceptions.TokenException;
import com.miniProject.smsToken.web.service.TokenSenderImpl;
import com.miniProject.smsToken.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.time.LocalDateTime;

@RestController
@RequestMapping("api/v1/user")
public class RegisterController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody UserDto userDto) {
        try {
            ResponseDto responseDto = userService.registerUser(userDto);
            return new ResponseEntity<>(responseDto, HttpStatus.OK);
        } catch (TokenException tokenException) {
            return new ResponseEntity<>(
                    new ApiResponse(tokenException.getMessage(),
                            LocalDateTime.now(),
                            false
                    ), HttpStatus.BAD_REQUEST);
        }
    }
}
