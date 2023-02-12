package com.hk.prj.userbook;


import com.hk.prj.userbook.user.User;
import com.hk.prj.userbook.user.UserRepository;
import com.hk.prj.userbook.user.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = UserService.class)
public class UserServiceTests {

    @Autowired
    UserService userService;

    @MockBean
    UserRepository userRepository;


    @Test
    public void getAllUsers_Test(){
        List<User> userList = new ArrayList<>();
        userList.add(new User(1L, "Hemant", "Kumar"));
        userList.add(new User(2L, "H", "K"));
        when(userRepository.findAll()).thenReturn(userList);

        assertThat("equals", userService.getUsers().size() == 2);
        assertThat("equals", userService.getUsers().get(0).getId()==1L);
        assertThat("equals", userService.getUsers().get(1).getId()==2L);
    }
}
