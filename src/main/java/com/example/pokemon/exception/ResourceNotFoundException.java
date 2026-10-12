package com.example.pokemon.exception;

// 自訂例外：專門代表「查無資料」。
// 跟一般的 IllegalArgumentException 分開，GlobalExceptionHandler 才能把它對應成 404
public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
