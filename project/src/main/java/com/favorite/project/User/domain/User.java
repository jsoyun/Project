package com.favorite.project.User.domain;

import com.favorite.project.User.dto.UserDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Builder
@Setter
public class User {
    //    private UUID uuid;
    private Long id;
    private String email;
    private String name;
    private String password;

    public User toUser(UserDTO userDTO) {
        return User.builder().email(userDTO.getEmail())
                .name(userDTO.getName())
                .password(userDTO.getPassword()).build();

    }
//
//    private UUID makeUUID() {
//        UUID newUUID = UUID.randomUUID();
//        return newUUID;
//
//    }


}