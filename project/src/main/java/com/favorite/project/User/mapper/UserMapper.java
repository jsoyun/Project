package com.favorite.project.User.mapper;

import com.favorite.project.User.domain.User;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Optional;

@Mapper
public interface UserMapper {

    @Select("SELECT * FROM Users")
    List<User> getAllUsers();

    @Select("SELECT * FROM Users WHERE id = #{userId}")
    Optional<User> findUserById(Long userId);

    @Select("SELECT * FROM Users WHERE email = #{email}")
    Optional<User> getByEmail(@Param("email") String email);

    @Insert("INSERT INTO Users (email, name, password) VALUES(#{user.email} , #{user.name}, #{user.password})")
    int insert(@Param("user") User users);

    @Update("UPDATE Users SET email= #{user.email}, name= #{user.name}, password = #{user.password} where id = #{userId} ")
    void updateUser(Long userId, User user);

    @Delete("DELETE FROM Users where id = #{userId} ")
    void deleteUser(Long userId);

    //xml 사용하고 싶은데 안됨.
    List<User> select();

}
