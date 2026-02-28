package com.mywallet.dto.user;

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
public class UserResponse {

    @Schema(description = "ID do usuário", example = "1")
    private Long id;
    
    @Schema(description = "Nome completo do usuário", example = "João Silva")
    private String name;
    
    @Schema(description = "Nome de usuário único", example = "joaosilva")
    private String username;
    
    @Schema(description = "Email do usuário", example = "joao.silva@email.com")
    private String email;
    
    @Schema(description = "Data de inclusão do registro")
    private LocalDateTime createdAt;
    
    @Schema(description = "ID do usuário que incluiu o registro")
    private Long createdBy;
    
    @Schema(description = "Data da última alteração do registro")
    private LocalDateTime updatedAt;
    
    @Schema(description = "ID do usuário que fez a última alteração")
    private Long updatedBy;

    public static UserResponse fromEntity(UserEntity user) {
        return UserResponse.builder()
            .id(user.getId())
            .name(user.getName())
            .username(user.getUsername())
            .email(user.getEmail())
            .createdAt(user.getCreatedAt())
            .createdBy(user.getCreatedBy())
            .updatedAt(user.getUpdatedAt())
            .updatedBy(user.getUpdatedBy())
            .build();
    }
}
