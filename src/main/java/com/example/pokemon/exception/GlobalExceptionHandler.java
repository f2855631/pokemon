package com.example.pokemon.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

// @RestControllerAdvice：全部 Controller 共用的例外處理。
// Service 丟出例外後，不用每個 Controller 自己 try/catch，會統一跑到這裡，
// 由這裡決定回傳哪個狀態碼（沒有這個 class 時，Spring 預設一律回 500）
@RestControllerAdvice
public class GlobalExceptionHandler {

    // 查無資料 -> 404 Not Found
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleNotFound(ResourceNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message", e.getMessage()));
    }

    // 送來的資料不合法（例如新增時給了不存在的寶可夢 id）-> 400 Bad Request
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, String>> handleBadRequest(IllegalArgumentException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("message", e.getMessage()));
    }
}
