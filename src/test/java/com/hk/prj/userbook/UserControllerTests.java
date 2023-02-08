package com.hk.prj.userbook;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hk.prj.userbook.user.User;
import com.hk.prj.userbook.user.UserController;
import com.hk.prj.userbook.user.UserNotFoundException;
import com.hk.prj.userbook.user.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = UserController.class, excludeAutoConfiguration = SecurityAutoConfiguration.class)
@ExtendWith(SpringExtension.class)
public class UserControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @BeforeEach
    public void preSetup(){
        List<User> users = new ArrayList<>();
        users.add(new User(1L, "User 1 first Name", "User 1 last name"));
        users.add(new User(2L, "User 2 first Name", "User 2 last name"));
        when(userService.getUsers()).thenReturn(users);
        when(userService.getUserById(1L)).thenReturn(users.get(0));
        when(userService.getUserById(2L)).thenReturn(users.get(1));
        when(userService.getUserById(3L)).thenThrow(UserNotFoundException.class);
        doThrow(UserNotFoundException.class).when(userService).deleteUser(3L);
        when(userService.saveUser(any(User.class))).thenReturn(new User(1L, "Hemant", "Kumar"));
    }

    @Test
    @DisplayName("Get all users")
    void getAllUsers_success() throws Exception {
        mockMvc.perform(get("/users"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().json(asJsonString(userService.getUsers())));
    }

    @Test
    @DisplayName("Get users by Id - found")
    void getUsersById_success() throws Exception {
        mockMvc.perform(get("/users/1"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().json(asJsonString(userService.getUserById(1L))));
    }

    @Test
    @DisplayName("Get users by Id - Not found")
    void getUsersById_failed() throws Exception {
        mockMvc.perform(get("/users/3"))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("Save user success")
    void saveUsers_success() throws Exception {
        mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(new User(1L, "Hemant", "Kumar"))))
                .andExpect(status().isCreated())
                .andExpect(header().exists("Location"));
    }

    @Test
    @DisplayName("Delete users by Id - Not found")
    void deleteUsersById_failed() throws Exception {
        mockMvc.perform(delete("/users/3"))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("Delete users by null Id - get exception")
    void deleteUsersByNullId_failed() throws Exception {
        mockMvc.perform(delete("/users/null"))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Delete users by Id - found")
    void deleteUsersById_success() throws Exception {
        mockMvc.perform(delete("/users/1"))
                .andExpect(status().isOk());
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
