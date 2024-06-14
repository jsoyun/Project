package com.favorite.project.User.RestController;

import com.favorite.project.User.LoginService;
import com.favorite.project.User.domain.User;
import com.favorite.project.User.dto.LoginRequestDTO;
import com.favorite.project.User.dto.LoginResponseDTO;
import com.favorite.project.Validator.SessionConst;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody @Valid LoginRequestDTO loginRequestDTO, HttpServletRequest request, HttpServletResponse response) {

        LoginResponseDTO loginResponseDTO = loginService.checkLoginForm(loginRequestDTO);
        User user = loginResponseDTO.toUser(loginResponseDTO);
        //기본은 true, 없으면 새로운 세션을 생성한다.
        HttpSession session = request.getSession();
        //세션에 데이터 저장
        session.setAttribute(SessionConst.USER_SESSION_ID, user);
        return ResponseEntity.ok(loginResponseDTO);
    }

    @GetMapping("/logout")
    public ModelAndView logout(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            //세션 삭제
            session.invalidate();
        }
        //쿠키 삭제
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("JSESSIONID")) {
                    cookie.setMaxAge(0);
                    response.addCookie(cookie);
                    break;
                }
            }

        }
        return new ModelAndView("redirect:/");


    }
}
