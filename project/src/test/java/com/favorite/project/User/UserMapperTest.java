package com.favorite.project.User;

import com.favorite.project.User.domain.User;
import com.favorite.project.User.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@Slf4j
//@MybatisTest
public class UserMapperTest {
    @Mock
    private UserMapper userMapper;

    @BeforeEach
    public void setUser() {
        MockitoAnnotations.openMocks(this);

    }

    @Test
    public void GetAllUsersTest() {
        //given
        List<User> userList = new ArrayList<>();
        User mockTest = User.builder().email("김생선").password("1234").build();
        User mockTest2 = User.builder().email("이선생").password("4321").build();
        userList.add(mockTest);
        userList.add(mockTest2);
        when(userMapper.getAllUsers()).thenReturn(userList);

        //when
        List<User> userList1 = userMapper.getAllUsers();

        //then
        verify(userMapper).getAllUsers();
        Assertions.assertThat(userList1.stream().filter(e -> e.getName().equals("김생선")));

        //log
        userList1.stream().peek(user -> {

            System.out.println("user.getEmail() = " + user.getEmail());
            System.out.println("user.getPassword() = " + user.getPassword());
        }).forEach(user -> {
            log.info("리스트={}");
        });


    }


    @Test
    public void GetUserByEmailTest() {
        //given
        User mocktest = User.builder().email("Mocktest").password("1234").build();
        when(userMapper.getByEmail("Mocktest")).thenReturn(Optional.ofNullable(mocktest));

        //when
        User UserByEmail = userMapper.getByEmail("Mocktest").orElseThrow();

        //then
        verify(userMapper).getByEmail("Mocktest");
        Assertions.assertThat(UserByEmail.getEmail()).isEqualTo("Mocktest");
        Assertions.assertThat(UserByEmail.getPassword()).isEqualTo("1234");

        //log
        log.info("getUser :" + UserByEmail.getEmail() + ", " + UserByEmail.getPassword() + ", ");
    }

    @Test
    public void InsertUserTest() {
        //given
        User mocktest = User.builder().email("user1").password("1234").build();
        when(userMapper.insert(mocktest)).thenReturn(1);
        //when
        int insert = userMapper.insert(mocktest);
        //then
        verify(userMapper).insert(mocktest);
        Assertions.assertThat(insert);

    }

    @Test
    public void GetByIdTest() {
        //given
        User mocktest = User.builder().email("user1").password("1234").build();
        when(userMapper.findUserById(1L)).thenReturn(Optional.ofNullable(mocktest));
        //when
        Optional<User> UserbyId = userMapper.findUserById(1L);
        //then
        verify(userMapper).findUserById(1L);
        Assertions.assertThat(mocktest.getEmail().equals(UserbyId.get().getEmail()));
        Assertions.assertThat(UserbyId);


    }


}
