package com.songcheShare.project.controllers;

import com.songcheShare.project.dtos.UserDto;
import com.songcheShare.project.entities.User;
import com.songcheShare.project.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/list")
    public List<User> getAllUsers() {
        return userService.list();
    }

    @GetMapping("/profile")
    public User getUser(String username) {
        return userService.findByUsername(username);
    }

    @GetMapping("/login")
    public ResponseEntity<String> login(@RequestParam("email") String email,
                                        @RequestParam("password") String password) {
        User user = userService.findByEmail(email);
        if (user == null) {
            return new ResponseEntity<>
                    ("An account with this email does not exist!",
                            HttpStatus.OK);
        }

        return new ResponseEntity<>("User with email: " +
                user.getEmail() + " and id: " + user.getId() +
                " entered the system.", HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UserDto userDto) {
        User user = userService.findByEmail(userDto.getEmail());

        if (user != null) {
            return new ResponseEntity<>
                    ("An account with this email already exists!",
                            HttpStatus.OK);
        }

        if (userService.save(userDto) != null) {
            return new ResponseEntity<>
                    ("New account create with email: " + userDto.getEmail(),
                            HttpStatus.OK);
        }

        return new ResponseEntity<>
                ("Information for profile is not complete!",
                        HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> delete(@RequestParam("id") Long id) {
        if (userService.findById(id) != null) {

            userService.delete(id);

            return new ResponseEntity<>
                    ("Profile deleted successfully with id: " + id.toString(),
                            HttpStatus.OK);
        }
        return new ResponseEntity<>
                ("Profile does not exist for id: " + id.toString(),
                        HttpStatus.BAD_REQUEST);
    }
}