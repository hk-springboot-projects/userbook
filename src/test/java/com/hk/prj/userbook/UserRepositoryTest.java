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
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
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
        User savedUser = userRepository.save(UserUtil.getUsers().get(0));
        assertThat(savedUser).isNotNull();
        assertThat(savedUser.getFirstName().equalsIgnoreCase("H1"));
        assertThat(savedUser.getLastName().equalsIgnoreCase("K1"));
        assertThat(savedUser.getId()).isNotNull();
    }

    @Test
    @DisplayName("save users list")
    public void testSaveUsers_success(){
        List<User> savedUsers = userRepository.saveAll(UserUtil.getUsers());
        assertThat(savedUsers.size() > 0);
        assertThat(savedUsers.get(0).getFirstName().equalsIgnoreCase("H1"));
        assertThat(savedUsers.get(0).getLastName().equalsIgnoreCase("K1"));
        assertThat(savedUsers.get(0).getId()).isNotNull();
    }


    @Test
    @DisplayName("find all")
    public void testEmptyFindAll(){
        userRepository.saveAll(UserUtil.getUsers());
        assertThat(userRepository.findAll().size()).isNotEqualTo(0);
    }

}
