package com.hk.prj.userbook;

import com.hk.prj.userbook.user.User;

import java.util.Arrays;
import java.util.List;

public class UserUtil {
    public static List<User> getUsers() {
        User user1 = User.builder().firstName("H1").lastName("K1").city("Delhi").gender("M").email("h@hk.com").country("IND").build();
        User user2 = User.builder().firstName("H2").lastName("K2").city("Mumbai").gender("M").email("h2@hk.com").country("IND").build();
        User user3 = User.builder().firstName("H3").lastName("K3").city("Hyderabad").gender("M").email("h3@hk.com").country("IND").build();
        return Arrays.asList(user1, user2, user3);
    }
}
