package com.mywallet.service.user;

import com.mywallet.dto.user.UserRequest;
import com.mywallet.dto.user.UserResponse;
import com.mywallet.entity.user.UserEntity;
import com.mywallet.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public UserResponse create(UserRequest request) {
        if (this.usuarioRepository.existsByUsername(request.getUsername())) {
            throw new IllegalArgumentException("Usuário já existe");
        }
        if (this.usuarioRepository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException("Email já existe");
        }

        UserEntity usuario = UserEntity.builder()
            .name(request.getName())
            .username(request.getUsername())
            .email(request.getEmail())
            .password(this.passwordEncoder.encode(request.getPassword()))
            .build();

        UserEntity saved = this.usuarioRepository.save(usuario);
        return UserResponse.fromEntity(saved);
    }

    @Transactional(readOnly = true)
    public UserResponse findById(Long id) {
        UserEntity usuario = this.usuarioRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));
        return UserResponse.fromEntity(usuario);
    }

    @Transactional(readOnly = true)
    public List<UserResponse> findAll() {
        return this.usuarioRepository.findAll().stream().map(UserResponse::fromEntity).toList();
    }

    @Transactional
    public UserResponse update(Long id, UserRequest request) {
        UserEntity usuario = this.usuarioRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));

        if (!usuario.getUsername().equals(request.getUsername()) && 
            this.usuarioRepository.existsByUsername(request.getUsername())) {
            throw new IllegalArgumentException("Usuário já existe");
        }
        if (!usuario.getEmail().equals(request.getEmail()) && 
            this.usuarioRepository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException("Email já existe");
        }

        usuario.setName(request.getName());
        usuario.setUsername(request.getUsername());
        usuario.setEmail(request.getEmail());
        if (request.getPassword() != null && !request.getPassword().isBlank()) {
            usuario.setPassword(this.passwordEncoder.encode(request.getPassword()));
        }

        UserEntity updated = this.usuarioRepository.save(usuario);
        return UserResponse.fromEntity(updated);
    }

    @Transactional
    public void delete(Long id) {
        if (!this.usuarioRepository.existsById(id)) {
            throw new IllegalArgumentException("Usuário não encontrado");
        }
        this.usuarioRepository.deleteById(id);
    }
}
