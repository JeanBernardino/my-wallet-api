package com.mywallet.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mywallet.dto.ApiError;
import com.mywallet.dto.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json;charset=UTF-8");

        ApiError error = ApiError.builder()
            .status(HttpServletResponse.SC_UNAUTHORIZED)
            .message("Token não informado ou inválido. Acesso não autorizado.")
            .build();

        ApiResponse<?> apiResponse = ApiResponse.error(error);

        response.getWriter().write(this.objectMapper.writeValueAsString(apiResponse));
    }
}
