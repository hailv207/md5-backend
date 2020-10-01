package com.md5backend.service;


import com.md5backend.model.User;

import java.util.Optional;

public interface UserService {
    Iterable<User> findAllUsers();
    Optional<User> findUserById(Long id);
    User saveUser(User user);
    void deleteUserById(Long id);
    void deleteUser(User user);
}
