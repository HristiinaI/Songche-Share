package com.songcheShare.project.services.impl;

import com.mysql.cj.util.StringUtils;
import com.songcheShare.project.dtos.UserDto;
import com.songcheShare.project.entities.User;
import com.songcheShare.project.repositories.UserRepository;
import com.songcheShare.project.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User save(UserDto userDto) {
        if (validateUserInfo(userDto)) {
            User user = new User();
            user.setFirstName(userDto.getFirstName());
            user.setLastName(userDto.getLastName());
            user.setEmail(userDto.getEmail());
            user.setPassword(userDto.getPassword());
            user.setUsername(userDto.getUsername());
            return userRepository.save(user);
        }
        return null;
    }

    private boolean validateUserInfo(UserDto userDto) {
        return !StringUtils.isEmptyOrWhitespaceOnly(userDto.getEmail())
                && !StringUtils.isEmptyOrWhitespaceOnly(userDto.getPassword())
                && !StringUtils.isEmptyOrWhitespaceOnly(userDto.getFirstName())
                && !StringUtils.isEmptyOrWhitespaceOnly(userDto.getLastName())
                && !StringUtils.isEmptyOrWhitespaceOnly(userDto.getUsername());
    }

    @Override
    public void delete(Long id) {
        userRepository
                .findById(id)
                .ifPresent(
                        user -> userRepository.delete(user));
    }

    @Override
    public User findById(Long id) {
        if (userRepository.findById(id).isPresent()) {
            return userRepository.findById(id).get();
        }
        return null;
    }
}
