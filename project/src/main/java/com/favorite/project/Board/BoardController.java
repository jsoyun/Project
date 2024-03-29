package com.favorite.project.Board;

import com.favorite.project.Board.dto.BoardAddDto;
import com.favorite.project.Board.dto.BoardResponseDto;
import com.favorite.project.User.domain.User;
import com.favorite.project.Validator.SessionConst;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@RequiredArgsConstructor
@RequestMapping
public class BoardController {
    private final BoardService boardService;

    @PostMapping("/board")
    public ResponseEntity<BoardResponseDto> postBoard(@RequestBody BoardAddDto boardAddDto, HttpServletRequest httpServletRequest) {
        HttpSession session = httpServletRequest.getSession(false);
        User user = (User) session.getAttribute(SessionConst.USER_SESSION_ID);
        boardAddDto.setUserId(user.getId());

        BoardResponseDto boardResponseDto = boardService.postBoard(boardAddDto);
        return new ResponseEntity<>(boardResponseDto, HttpStatus.OK);


    }

}
