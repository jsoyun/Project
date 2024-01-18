package com.favorite.project.User;

//import com.favorite.project.mapper.UserMapper;

import com.favorite.project.Closet.ClosetService;
import com.favorite.project.User.domain.User;
import com.favorite.project.Closet.domain.UserCloset;
import com.favorite.project.User.dto.UserDTO;
import com.favorite.project.User.exceptions.SQLExceptionHandler;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping(value = "/users")
public class UserController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    //json객체
    @GetMapping
    public List<User> getAllUsers() {

//        return userService.getAllUsers();

//        try {
//            return userService.select();
//        } catch (RuntimeException runtimeException) {
//            logger.error(String.valueOf(runtimeException));
//            return
//
//
//        }

        return userService.select();


    }


    @PostMapping
    public boolean insertUserData(@RequestBody UserDTO userDTO) {
        return userService.insertOneUser(userDTO);

    }

    //뷰로 가져오기
    @GetMapping("/showUser")
    public ModelAndView showUser() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("userList");
        return modelAndView;
    }


//    @GetMapping(value = "/testSelect")
//    public List<Users> test() {
//        return userService.select();
//    }   @GetMapping(value = "/testSelect")
//    public List<Users> test() {
//        return userService.select();
//    }


}
