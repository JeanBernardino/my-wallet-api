package com.mywallet.dto.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Schema(description = "Credenciais de login")
public class LoginRequest {

    @NotBlank(message = "Username é obrigatório")
    @Schema(description = "Nome de usuário", example = "joao.silva")
    private String username;

    @NotBlank(message = "Senha é obrigatória")
    @Schema(description = "Senha do usuário", example = "senha123")
    private String password;
}
