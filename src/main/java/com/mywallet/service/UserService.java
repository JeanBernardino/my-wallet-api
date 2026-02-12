package com.mywallet.service;

import com.mywallet.dto.UsuarioRequest;
import com.mywallet.dto.UsuarioResponse;
import com.mywallet.entity.user.UserEntity;
import com.mywallet.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository usuarioRepository;

    @Transactional
    public UsuarioResponse criar(UsuarioRequest request) {
        if (usuarioRepository.existsByUsername(request.getUsername())) {
            throw new IllegalArgumentException("Usuário já existe");
        }
        if (usuarioRepository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException("Email já existe");
        }

        UserEntity usuario = UserEntity.builder()
                .name(request.getName())
                .username(request.getUsername())
                .email(request.getEmail())
                .password(request.getPassword()) // TODO: Criptografar senha
                .build();

        UserEntity saved = usuarioRepository.save(usuario);
        return UsuarioResponse.fromEntity(saved);
    }

    @Transactional(readOnly = true)
    public UsuarioResponse buscarPorId(Long id) {
        UserEntity usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));
        return UsuarioResponse.fromEntity(usuario);
    }

    @Transactional(readOnly = true)
    public List<UsuarioResponse> listarTodos() {
        return usuarioRepository.findAll().stream()
                .map(UsuarioResponse::fromEntity)
                .collect(Collectors.toList());
    }

    @Transactional
    public UsuarioResponse atualizar(Long id, UsuarioRequest request) {
        UserEntity usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));

        if (!usuario.getUsername().equals(request.getUsername()) && 
            usuarioRepository.existsByUsername(request.getUsername())) {
            throw new IllegalArgumentException("Usuário já existe");
        }
        if (!usuario.getEmail().equals(request.getEmail()) && 
            usuarioRepository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException("Email já existe");
        }

        usuario.setName(request.getName());
        usuario.setUsername(request.getUsername());
        usuario.setEmail(request.getEmail());
        if (request.getPassword() != null && !request.getPassword().isBlank()) {
            usuario.setPassword(request.getPassword()); // TODO: Criptografar senha
        }

        UserEntity updated = usuarioRepository.save(usuario);
        return UsuarioResponse.fromEntity(updated);
    }

    @Transactional
    public void deletar(Long id) {
        if (!usuarioRepository.existsById(id)) {
            throw new IllegalArgumentException("Usuário não encontrado");
        }
        usuarioRepository.deleteById(id);
    }
}
