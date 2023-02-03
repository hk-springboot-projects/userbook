package com.hk.prj.userbook;


import com.hk.prj.userbook.user.UserRepository;
import com.hk.prj.userbook.user.UserService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = UserService.class)
public class UserServiceTests {

    @Autowired
    UserService userService;

    @MockBean
    UserRepository userRepository;

    @BeforeEach
    public void preSetup(){
        when(userRepository.findAll()).thenReturn(new ArrayList<>());
    }

    @Test
    public void getAllUsers_Test(){
        assertThat("equals", userService.getUsers().size() == new ArrayList<>().size());
    }
}
