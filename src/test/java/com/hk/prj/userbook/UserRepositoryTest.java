package com.hk.prj.userbook;

import com.hk.prj.userbook.user.User;
import com.hk.prj.userbook.user.UserRepository;
import org.h2.engine.UserBuilder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;

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
    @DisplayName("Empty find all")
    public void testEmptyFindAll(){
        assertThat(userRepository.findAll().size()).isEqualTo(0);
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

}
