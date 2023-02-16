package com.hk.prj.userbook;

import com.hk.prj.userbook.user.User;
import com.hk.prj.userbook.user.UserRepository;
import org.h2.engine.UserBuilder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest(classes ={UserbookApplication.class} )
public class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Test
    @DisplayName("Autowired User Repository")
    public void testAutowiredUserRepository(){
        assertThat(userRepository).isNotNull();
    }

    @Test
    @DisplayName("save user")
    public void testSaveUser_success(){
        User user = User.builder().firstName("Hemant").lastName("Kumar").build();
        User savedUser = userRepository.save(user);
        assertThat(savedUser).isNotNull();
        assertThat(savedUser.getFirstName().equalsIgnoreCase("Hemant"));
        assertThat(savedUser.getLastName().equalsIgnoreCase("Kumar"));
        assertThat(savedUser.getId()).isNotNull();
    }

    @Test
    @DisplayName("save users list")
    public void testSaveUsers_success(){
        User user1 = User.builder().firstName("Hemant").lastName("Kumar").build();
        User user2 = User.builder().firstName("User 1").lastName("last name").build();
        User user3 = User.builder().firstName("User 2").lastName("last name").build();
        List<User> savedUsers = userRepository.saveAll(Arrays.asList(user1, user2, user3));
        assertThat(savedUsers.size() > 0);
        assertThat(savedUsers.get(0).getFirstName().equalsIgnoreCase("Hemant"));
        assertThat(savedUsers.get(0).getLastName().equalsIgnoreCase("Kumar"));
        assertThat(savedUsers.get(0).getId()).isNotNull();
    }

    @Test
    @DisplayName("find all")
    public void testEmptyFindAll(){
        assertThat(userRepository.findAll().size()).isNotEqualTo(0);
    }

}
