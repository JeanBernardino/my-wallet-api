package com.mywallet.controller;

import com.mywallet.dto.ApiResponse;
import com.mywallet.dto.user.UserRequest;
import com.mywallet.dto.user.UserResponse;
import com.mywallet.service.user.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@Tag(name = "Usuários", description = "Gerenciamento de usuários")
public class UserController {

    private final UserService usuarioService;

    @PostMapping
    @Operation(summary = "Criar novo usuário", description = "Cria um novo usuário no sistema")
    @ApiResponses(value = {
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "201", description = "Usuário criado com sucesso"),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Dados inválidos ou usuário/email já existe")
    })
    public ResponseEntity<ApiResponse<UserResponse>> create(@Valid @RequestBody UserRequest request) {
        UserResponse response = this.usuarioService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.success(response));
    }

    @GetMapping
    @Operation(summary = "Listar todos os usuários", description = "Retorna uma lista com todos os usuários cadastrados")
    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Lista retornada com sucesso")
    public ResponseEntity<ApiResponse<List<UserResponse>>> findAll() {
        List<UserResponse> response = this.usuarioService.findAll();
        return ResponseEntity.ok(ApiResponse.success(response));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar usuário por ID", description = "Retorna um usuário específico pelo seu ID")
    @ApiResponses(value = {
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Usuário encontrado"),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Usuário não encontrado")
    })
    public ResponseEntity<ApiResponse<UserResponse>> findById(@Parameter(description = "ID do usuário") @PathVariable Long id) {
        UserResponse response = this.usuarioService.findById(id);
        return ResponseEntity.ok(ApiResponse.success(response));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar usuário", description = "Atualiza os dados de um usuário existente")
    @ApiResponses(value = {
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Usuário atualizado com sucesso"),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Dados inválidos ou usuário não encontrado")
    })
    public ResponseEntity<ApiResponse<UserResponse>> update(
        @Parameter(description = "ID do usuário") @PathVariable Long id,
        @Valid @RequestBody UserRequest request
    ) {
        UserResponse response = this.usuarioService.update(id, request);
        return ResponseEntity.ok(ApiResponse.success(response));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar usuário", description = "Remove um usuário do sistema")
    @ApiResponses(value = {
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "204", description = "Usuário deletado com sucesso"),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Usuário não encontrado")
    })
    public ResponseEntity<ApiResponse<Void>> delete(@Parameter(description = "ID do usuário") @PathVariable Long id) {
        this.usuarioService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(ApiResponse.success(null));
    }
}
