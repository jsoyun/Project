package com.favorite.project.User.RestController;

import com.favorite.project.User.SignupService;
import com.favorite.project.User.UserService;
import com.favorite.project.User.domain.User;
import com.favorite.project.User.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/users")
public class UserController {
    private final UserService userService;
    private final SignupService signupService;


    @GetMapping
    public List<User> getUsers() {
        return userService.select();

    }

    @PostMapping
    public ResponseEntity<HttpStatus> signup(@RequestBody UserDTO userDTO) {
        signupService.signup(userDTO);
        return new ResponseEntity<>(HttpStatus.OK);

    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable Long userId) {
        User user = userService.findUserById(userId);
        //TODO: 값이 없을 때 쉽게 처리할 수 있지 않을까..
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    @PatchMapping("/{userId}")
    public ResponseEntity<User> updateUserById(@PathVariable Long userId, @RequestBody Map<String, String> userUpdateDto) {
        User user = userService.updateUserById(userId, userUpdateDto);
        return ResponseEntity.ok(user);

    }

    @DeleteMapping("/{userEmail}")
    public ResponseEntity<HttpStatus> deleteUserById(@PathVariable String userEmail) {
        userService.deleteUserById(userEmail);
        return ResponseEntity.ok(HttpStatus.OK);

    }


}
