package com.favorite.project.User;

import com.favorite.project.User.domain.User;
import com.favorite.project.User.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 유저 데이터 관리 (조회)
 */

@Service
@RequiredArgsConstructor
public class UserService {

    //응집도 : 관련된 게 잘 모여있어야한다.
    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final UserMapper userMapper;


    public Optional<User> getUserByEmail(User user) {

        return Optional.ofNullable(userMapper.getByEmail(user.getEmail()));
    }

    public List<User> getAllUsers() {
        return userMapper.getAllUsers();
    }


    public List<User> select() {
        try {

            return userMapper.select();

        } catch (Exception exception) {
            logger.error("List<user> select(): {}", exception.getMessage());
            throw new RuntimeException(exception);

        }


    }

    public User findUserById(Long userId) {
        return userMapper.findUserById(userId).orElse(null);
    }

    public User updateUserById(Long userId, Map<String, String> updateUser) {
        User existingUser = userMapper.findUserById(userId).orElseThrow(NoSuchElementException::new);
        updateUser.forEach((key, value) -> {
            switch (key) {
                case "email":
                    if (!Objects.equals(value, "")) {
                        existingUser.setEmail(value);
                    }

                    break;
                case "password":
                    if (!Objects.equals(value, "")) {
                        existingUser.setPassword(value);
                    }

                    break;
                case "name":
                    if (!Objects.equals(value, "")) {
                        existingUser.setName(value);
                    }


            }


        });


        userMapper.updateUser(userId, existingUser);
        return existingUser;


    }
}
