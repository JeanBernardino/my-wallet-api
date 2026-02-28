package com.mywallet.exception;

import com.mywallet.dto.ApiError;
import com.mywallet.dto.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ApiResponse<Object>> handleBadCredentialsException(BadCredentialsException ex, HttpServletRequest request) {
        ApiError error = ApiError.builder()
            .status(HttpStatus.UNAUTHORIZED.value())
            .message("Usuário ou senha inválidos")
            .build();
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ApiResponse.error(error));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiResponse<Object>> handleIllegalArgumentException(IllegalArgumentException ex, HttpServletRequest request) {
        ApiError error = ApiError.builder()
            .status(HttpStatus.BAD_REQUEST.value())
            .message(ex.getMessage())
            .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ApiResponse.error(error));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Object>> handleValidationException(MethodArgumentNotValidException ex, HttpServletRequest request) {
        Map<String, String> validationErrors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(err -> {
            String fieldName = ((FieldError) err).getField();
            String errorMessage = err.getDefaultMessage();
            validationErrors.put(fieldName, errorMessage);
        });

        ApiError error = ApiError.builder()
            .status(HttpStatus.BAD_REQUEST.value())
            .message("Erro de validação nos campos")
            .details(validationErrors)
            .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ApiResponse.error(error));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Object>> handleGenericException(Exception ex, HttpServletRequest request) {
        ApiError error = ApiError.builder()
            .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
            .message("Erro interno do servidor")
            .build();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ApiResponse.error(error));
    }
}
