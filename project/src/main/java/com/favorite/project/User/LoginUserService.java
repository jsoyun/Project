package com.favorite.project.User;

import com.favorite.project.User.domain.User;
import com.favorite.project.User.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

/**
 * 같은 email을 가진 User있는지 조회
 */
@Service
@RequiredArgsConstructor
public class LoginUserService {
    private final UserMapper userMapper;

    public User getUserByEmail(User user) {

        return userMapper.getByEmail(user.getEmail()).orElseThrow(() -> new NoSuchElementException("해당하는 이메일이 없습니다."));
    }
}
