package com.songcheShare.project.services;

import com.songcheShare.project.dtos.UserDto;
import com.songcheShare.project.entities.User;

import java.util.List;

public interface UserService {
    User findByEmail(String email);

    User save(UserDto registration);

    void delete(Long id);

    User findById(Long id);

    List<User> list();

    User findByUsername(String username);
}
