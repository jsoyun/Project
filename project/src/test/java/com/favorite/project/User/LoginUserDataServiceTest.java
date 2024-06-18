package com.favorite.project.User;

import com.favorite.project.User.domain.User;
import com.favorite.project.User.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@Slf4j
@ExtendWith(MockitoExtension.class) //초기화로 mock 객체 가져옴
public class LoginUserDataServiceTest {

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private LoginUserService loginUserService;


    @Test
    void testGetUserByEmail() {
        //Given
        String userEmail = "test@example.com";
        String password = "123";
        User user = User.builder().email(userEmail).password(password).build();
        when(userMapper.getByEmail(userEmail)).thenReturn(Optional.ofNullable(user)); //조회해서 값이 있다고 가정했을 때

        //when
        User result = loginUserService.getUserByEmail(user);

        //Then
        assertEquals(userEmail, result.getEmail());
        assertEquals(password, result.getPassword());

        //userMapper 목 객체에 대해 getByEmail메서드가 정확히 1번 호출되었는지 검증
        verify(userMapper, times(1)).getByEmail(userEmail);


    }
}
