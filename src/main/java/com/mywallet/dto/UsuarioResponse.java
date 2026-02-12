package com.mywallet.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import com.mywallet.entity.user.UserEntity;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Dados de resposta do usuário")
public class UsuarioResponse {

    @Schema(description = "ID do usuário", example = "1")
    private Long id;
    
    @Schema(description = "Nome completo do usuário", example = "João Silva")
    private String nome;
    
    @Schema(description = "Nome de usuário único", example = "joaosilva")
    private String usuario;
    
    @Schema(description = "Email do usuário", example = "joao.silva@email.com")
    private String email;
    
    @Schema(description = "Data de inclusão do registro")
    private LocalDateTime inclusaoData;
    
    @Schema(description = "ID do usuário que incluiu o registro")
    private Long inclusaoUsuarioId;
    
    @Schema(description = "Data da última alteração do registro")
    private LocalDateTime alteracaoData;
    
    @Schema(description = "ID do usuário que fez a última alteração")
    private Long alteracaoUsuarioId;

    public static UsuarioResponse fromEntity(UserEntity user) {
        return UsuarioResponse.builder()
                .id(user.getId())
                .nome(user.getName())
                .usuario(user.getUsername())
                .email(user.getEmail())
                .inclusaoData(user.getInclusaoData())
                .inclusaoUsuarioId(user.getInclusaoUsuarioId())
                .alteracaoData(user.getAlteracaoData())
                .alteracaoUsuarioId(user.getAlteracaoUsuarioId())
                .build();
    }
}
