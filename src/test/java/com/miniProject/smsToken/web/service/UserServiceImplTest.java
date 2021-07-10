package com.miniProject.smsToken.web.service;

import com.miniProject.smsToken.data.model.User;
import com.miniProject.smsToken.data.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.Lazy;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

@DataMongoTest
@Slf4j
@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {
    @Mock
    @Lazy
    UserRepository userRepository;

    @InjectMocks
    UserServiceImpl userServiceImpl;

    User user;
    User user1;

    @BeforeEach
    void setUp() {
        user = new User();
        user1 = new User();
;
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void findAll() {
        userServiceImpl.findAll();
        verify(userRepository).findAll();
    }

}