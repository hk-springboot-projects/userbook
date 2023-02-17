package com.hk.prj.userbook;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hk.prj.userbook.user.User;
import com.hk.prj.userbook.user.UserController;
import com.hk.prj.userbook.user.UserNotFoundException;
import com.hk.prj.userbook.user.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * WebMvcTest = SpringBootTest + AutoConfigureMockMvc
 */
@WebMvcTest(controllers = UserController.class, excludeAutoConfiguration = SecurityAutoConfiguration.class)
public class UserControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    @DisplayName("Get all users")
    void getAllUsers_success() throws Exception {
        when(userService.getUsers()).thenReturn(UserUtil.getUsers());
        mockMvc.perform(get("/users"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().json(asJsonString(UserUtil.getUsers())));
    }

    @Test
    @DisplayName("Get users by Id - found")
    void getUsersById_success() throws Exception {
        when(userService.getUserById(1L)).thenReturn(UserUtil.getUsers().get(0));
        mockMvc.perform(get("/users/1"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().json(asJsonString(UserUtil.getUsers().get(0))));
    }

    @Test
    @DisplayName("Get users by Id - Not found")
    void getUsersById_failed() throws Exception {
        when(userService.getUserById(3L)).thenThrow(UserNotFoundException.class);
        mockMvc.perform(get("/users/3"))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("Save user success")
    void saveUsers_success() throws Exception {
        when(userService.saveUser(any(User.class))).thenReturn(UserUtil.getUsers().get(0));
        mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(UserUtil.getUsers().get(0))))
                .andExpect(status().isCreated())
                .andExpect(header().exists("Location"));
    }

    @Test
    @DisplayName("Save blank first name user - bad request")
    void saveBlankFirstNameUsers_returnBadRequest() throws Exception {
        User user = UserUtil.getUsers().get(0);
        user.setFirstName("");
        mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(user)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errorCode").value("first name should have atleast 2 characters"));
    }

    @Test
    @DisplayName("Save first name 1 letter user - bad request")
    void saveFirstName1LetterUser_returnBadRequest() throws Exception {
        User user = UserUtil.getUsers().get(0);
        user.setFirstName("H");
        mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(user)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errorCode").value("first name should have atleast 2 characters"));
    }

    @Test
    @DisplayName("Save blank Last name user - bad request")
    void saveBlankLastNameUsers_returnBadRequest() throws Exception {
        User user = UserUtil.getUsers().get(0);
        user.setLastName("");
        mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(user)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errorCode").value("last name can't be empty"));
    }

    @Test
    @DisplayName("Save blank name user - bad request")
    void saveBlankNameUsers_returnBadRequest() throws Exception {
        User user = UserUtil.getUsers().get(0);
        user.setFirstName("");
        user.setLastName("");
        mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(user)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errorCode").value("first name should have atleast 2 characters, last name can't be empty"));
    }

    @Test
    @DisplayName("Post user with Id - bad request and message")
    void saveUsersWithId_returnBadRequestWithMessage() throws Exception {
        User user = UserUtil.getUsers().get(0);
        user.setId(1L);
        mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(user)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errorDescription").value("id is present, Use PUT instead of POST"));
    }

    @Test
    @DisplayName("Delete users by Id - Not found")
    void deleteUsersById_failed() throws Exception {
        doThrow(UserNotFoundException.class).when(userService).deleteUser(3L);
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
