package com.favorite.project.User;

import com.favorite.project.User.domain.User;
import com.favorite.project.User.dto.UserDTO;
import com.favorite.project.User.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * 회원가입 : 회원정보 받아서 등록
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class SignupService {

    //TODO: 등록시 이메일 검사 및 비밀번호 유효성 검증하기
    private Logger logger = LoggerFactory.getLogger(getClass());

    private final UserMapper userMapper;


    public void signup(UserDTO userDTO) {
        User user = User.builder().build().toUser(userDTO);
        userMapper.insert(user);


    }

}
