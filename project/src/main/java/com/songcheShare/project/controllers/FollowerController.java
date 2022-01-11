package com.songcheShare.project.controllers;

import com.songcheShare.project.dtos.FollowerDto;
import com.songcheShare.project.services.FollowerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/followers")
public class FollowerController {

    @Autowired
    private FollowerService followerService;

    @GetMapping("list-followers")
    public List<String> listFollowers(@RequestParam("id") Long id) {
        return followerService.listFollowers(id);
    }

    @GetMapping("list-following")
    public List<String> listFollowing(@RequestParam("id") Long id) {
        return followerService.listFollowing(id);
    }

    @PostMapping("/follow")
    public ResponseEntity<String> followUser(@RequestBody FollowerDto followerDto) {
        if (!followerService.validateInfo(followerDto)) {
            return new ResponseEntity<>("Some information is missing!",
                    HttpStatus.BAD_REQUEST);
        }

        if (!followerService.usersExist(followerDto)) {
            return new ResponseEntity<>("There are no such user/s!",
                    HttpStatus.BAD_REQUEST);
        }

        if (!followerService.isFollowUnique(followerDto)) {
            return new ResponseEntity<>("You are already following user with id: " + followerDto.getUserId(),
                    HttpStatus.BAD_REQUEST);
        }

        if (followerService.isEqual(followerDto)) {
            return new ResponseEntity<>("You cannot follow yourself!",
                    HttpStatus.BAD_REQUEST);
        }

        followerService.save(followerDto);
        return new ResponseEntity<>("Followed successful!",
                HttpStatus.OK);
    }

    @DeleteMapping("/unfollow")
    public ResponseEntity<String> unfollow(@RequestBody FollowerDto followerDto) {
        followerService.unfollow(followerDto);
        return new ResponseEntity<>("Unfollow successful!",
                HttpStatus.OK);
    }
}
