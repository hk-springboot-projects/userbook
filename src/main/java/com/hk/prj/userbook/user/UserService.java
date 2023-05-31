package com.hk.prj.userbook.user;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getUsers(PageRequest pageRequest) {
        return userRepository.findAll(pageRequest).stream().toList();
    }

    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with id " + id));
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public void deleteUser(long userId) {
        User user = getUserById(userId);
        userRepository.delete(user);
    }
}
