package com.mywallet.dto.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Resposta com o token JWT")
public class LoginResponse {

    @Schema(description = "Token JWT para autenticação", example = "eyJhbGciOiJIUzI1NiJ9...")
    private String token;

    @Schema(description = "Tipo do token", example = "Bearer")
    private String type;

    @Schema(description = "Nome de usuário autenticado", example = "joao.silva")
    private String username;

    @Schema(description = "Tempo de expiração em milissegundos")
    private long expiresIn;
}
