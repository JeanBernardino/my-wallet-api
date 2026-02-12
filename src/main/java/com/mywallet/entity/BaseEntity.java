package com.mywallet.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@MappedSuperclass
public abstract class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "inclusao_data", nullable = false, updatable = false)
    private LocalDateTime inclusaoData;

    @Column(name = "inclusao_usuario_id", updatable = false)
    private Long inclusaoUsuarioId;

    @Column(name = "alteracao_data")
    private LocalDateTime alteracaoData;

    @Column(name = "alteracao_usuario_id")
    private Long alteracaoUsuarioId;

    @PrePersist
    protected void onCreate() {
        inclusaoData = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        alteracaoData = LocalDateTime.now();
    }
}
