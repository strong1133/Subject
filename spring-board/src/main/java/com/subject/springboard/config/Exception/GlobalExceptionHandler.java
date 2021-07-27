package com.subject.springboard.config.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
@RestController
public class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public Map<String, String> handleException (Exception e){
        Map<String, String> map = new HashMap<>();
        if (e.getMessage()==null){
            map.put("msg","잘못된 접근입니다!");
        }else {
            map.put("msg",e.getMessage());
        }
        return map;
    }
}
