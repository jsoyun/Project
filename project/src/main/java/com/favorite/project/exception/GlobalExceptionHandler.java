package com.favorite.project.exception;

import org.apache.ibatis.exceptions.PersistenceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ResponseEntity<ExceptionResponse> processValidationError(MethodArgumentNotValidException exception) {
        List<String> Messages = new ArrayList<>();
        exception.getBindingResult().getFieldErrors().forEach((e) -> {
            String errorMessage = e.getField() + ": " + e.getDefaultMessage();
            Messages.add(errorMessage);
        });
        ExceptionResponse elementNotFound = ExceptionResponse.builder().message("유효한 값이 아닙니다").exceptions(Messages).status(HttpStatus.BAD_REQUEST).build();
        return new ResponseEntity<>(elementNotFound, elementNotFound.getStatus());

    }

    @ExceptionHandler(BindException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ResponseEntity<ExceptionResponse> BindException(BindException exception) {
        List<String> Messages = new ArrayList<>();
        exception.getBindingResult().getFieldErrors().forEach((e) -> {
            String errorMessage = e.getField() + ": " + e.getDefaultMessage();

            Messages.add(errorMessage);
        });
        ExceptionResponse elementNotFound = ExceptionResponse.builder().message("MyBatis BindingException 발생").exceptions(Messages).status(HttpStatus.BAD_REQUEST).build();
        return new ResponseEntity<>(elementNotFound, elementNotFound.getStatus());

    }


    @ExceptionHandler(NoSuchElementException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND) //명시적 선언, 하지만 ErrorResponse의 status세부 설정이 우선권 가진다.
    public ResponseEntity<ExceptionResponse> handleNoSuchElementException(NoSuchElementException exception) {
        ExceptionResponse elementNotFound = ExceptionResponse.builder().message(exception.getMessage()).exceptions(Collections.singletonList("Element not found")).status(HttpStatus.NOT_FOUND).build();
        return new ResponseEntity<>(elementNotFound, elementNotFound.getStatus());

    }

    @ExceptionHandler(SQLException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ResponseEntity<ExceptionResponse> SQLException(SQLException exception) {
        List<String> Messages = new ArrayList<>();
        Messages.add(exception.getMessage());

        ExceptionResponse elementNotFound = ExceptionResponse.builder().message("SQL 요청실패").exceptions(Messages).status(HttpStatus.BAD_REQUEST).build();
        return new ResponseEntity<>(elementNotFound, elementNotFound.getStatus());

    }

    @ExceptionHandler(PersistenceException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND) //명시적 선언, 하지만 ErrorResponse의 status세부 설정이 우선권 가진다.
    public ResponseEntity<ExceptionResponse> PersistenceException(PersistenceException exception) {
        List<String> Messages = new ArrayList<>();
        Messages.add(exception.getMessage());

        ExceptionResponse elementNotFound = ExceptionResponse.builder().message("매핑 실패").exceptions(Messages).status(HttpStatus.BAD_REQUEST).build();
        return new ResponseEntity<>(elementNotFound, elementNotFound.getStatus());

    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ResponseEntity<ExceptionResponse> IllegalArgumentException(IllegalArgumentException exception) {
        List<String> Messages = new ArrayList<>();
        Messages.add(exception.getMessage());

        ExceptionResponse elementNotFound = ExceptionResponse.builder().message("유효하지 않은 값").exceptions(Messages).status(HttpStatus.BAD_REQUEST).build();
        return new ResponseEntity<>(elementNotFound, elementNotFound.getStatus());

    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<ExceptionResponse> handleNullPointerException(NullPointerException exception) {
        List<String> Messages = new ArrayList<>();
        Messages.add(exception.getMessage());
        ExceptionResponse exceptionResponse = ExceptionResponse.builder().message("Null pointer exception 발생").exceptions(Messages).status(HttpStatus.BAD_REQUEST).build();
        return new ResponseEntity<>(exceptionResponse, exceptionResponse.getStatus());
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ExceptionResponse> handleNullPointerException(RuntimeException exception) {
        List<String> Messages = new ArrayList<>();
        Messages.add(exception.getMessage());
        ExceptionResponse exceptionResponse = ExceptionResponse.builder().message("RuntimeException 발생").exceptions(Messages).status(HttpStatus.BAD_REQUEST).build();
        return new ResponseEntity<>(exceptionResponse, exceptionResponse.getStatus());
    }


}
