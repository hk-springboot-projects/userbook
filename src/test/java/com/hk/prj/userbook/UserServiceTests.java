package com.hk.prj.userbook;


import com.hk.prj.userbook.user.User;
import com.hk.prj.userbook.user.UserNotFoundException;
import com.hk.prj.userbook.user.UserRepository;
import com.hk.prj.userbook.user.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = UserService.class)
public class UserServiceTests {

    @Autowired
    UserService userService;

    @MockBean
    UserRepository userRepository;


    @Test
    public void getAllUsers_Test() {
        when(userRepository.findAll()).thenReturn(UserUtil.getUsers());
        assertThat(userService.getUsers().size()).isNotEqualTo(0);
    }

    @Test
    public void getUsersById_success() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(UserUtil.getUsers().get(0)));
        User user = userService.getUserById(1L);
        assertThat(user.getFirstName()).isEqualTo("H1");
        assertThat(user.getLastName()).isEqualTo("K1");
    }

    @Test
    public void getUsersById_failed() {
        when(userRepository.findById(2L)).thenThrow(new UserNotFoundException("User not found with id 2"));
        try {
            userService.getUserById(2L);
        } catch (UserNotFoundException u) {
            assertThat(u).isNotNull();
            assertThat(u.getMessage()).isEqualTo("User not found with id 2");
        }
    }

    @Test
    public void deleteUsersById_failed() {
        when(userRepository.findById(2L)).thenThrow(new UserNotFoundException("User not found with id 2"));
        try {
            userService.deleteUser(2L);
        } catch (UserNotFoundException u) {
            assertThat(u).isNotNull();
            assertThat(u.getMessage()).isEqualTo("User not found with id 2");
        }
    }
}
