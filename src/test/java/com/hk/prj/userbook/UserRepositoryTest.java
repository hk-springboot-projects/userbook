package com.hk.prj.userbook;

import com.hk.prj.userbook.user.User;
import com.hk.prj.userbook.user.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.AUTO_CONFIGURED)
public class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Test
    @DisplayName("Autowired User Repository")
    public void testAutowiredUserRepository(){
        assertThat(userRepository).isNotNull();
    }

    @Test
    @DisplayName("find all")
    public void initial_data_count_success(){
        assertThat(userRepository.findAll().size()).isEqualTo(25);
    }

    @Test
    @DisplayName("save user")
    public void testSaveUser_success(){
        User savedUser = userRepository.save(UserUtil.getUsers().get(0));
        assertThat(savedUser).isNotNull();
        assertThat(savedUser.getFirstName()).isEqualTo("H1");
        assertThat(savedUser.getLastName()).isEqualTo("K1");
        assertThat(savedUser.getId()).isNotNull();
    }

    @Test
    @DisplayName("save users list")
    public void testSaveUsers_success(){
        List<User> savedUsers = userRepository.saveAll(UserUtil.getUsers());
        assertThat(savedUsers.size()).isEqualTo(3);
        assertThat(savedUsers.get(0).getFirstName()).isEqualTo("H1");
        assertThat(savedUsers.get(0).getLastName()).isEqualTo("K1");
        assertThat(savedUsers.get(0).getId()).isNotNull();
    }

    @Test
    @DisplayName("delete users success")
    public void deleteUsers_success(){
        List<User> savedUsers = userRepository.findAll();
        assertThat(savedUsers.size()).isEqualTo(25);
        userRepository.delete(savedUsers.get(0));
        assertThat(userRepository.findAll().size()).isEqualTo(24);
    }




}
