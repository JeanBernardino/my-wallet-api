package com.mywallet.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Dados para criação ou atualização de usuário")
public class UsuarioRequest {

    @Schema(description = "Nome completo do usuário", example = "João Silva", required = true)
    @NotBlank(message = "Nome é obrigatório")
    @Size(max = 100, message = "Nome deve ter no máximo 100 caracteres")
    private String name;

    @Schema(description = "Nome de usuário único", example = "joaosilva", required = true)
    @NotBlank(message = "Usuário é obrigatório")
    @Size(min = 3, max = 50, message = "Usuário deve ter entre 3 e 50 caracteres")
    private String username;

    @Schema(description = "Email do usuário", example = "joao.silva@email.com", required = true)
    @NotBlank(message = "Email é obrigatório")
    @Email(message = "Email inválido")
    @Size(max = 100, message = "Email deve ter no máximo 100 caracteres")
    private String email;

    @Schema(description = "Senha do usuário", example = "senha123", required = true, minLength = 6)
    @NotBlank(message = "Senha é obrigatória")
    @Size(min = 6, message = "Senha deve ter no mínimo 6 caracteres")
    private String password;
}
