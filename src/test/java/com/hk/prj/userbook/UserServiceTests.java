package com.hk.prj.userbook;


import com.hk.prj.userbook.user.UserRepository;
import com.hk.prj.userbook.user.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = UserService.class)
public class UserServiceTests {

    @Autowired
    UserService userService;

    @MockBean
    UserRepository userRepository;


    @Test
    public void getAllUsers_Test(){
        when(userRepository.findAll()).thenReturn(UserUtil.getUsers());
        assertThat(userService.getUsers().size()).isNotEqualTo(0);
    }
}
