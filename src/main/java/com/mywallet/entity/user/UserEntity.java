package com.mywallet.entity.user;

import com.mywallet.entity.BaseEntity;

import jakarta.persistence.*;
import lombok.*;

@Entity(name = "UserEntity")
@Table(name = "appuser", uniqueConstraints = {
    @UniqueConstraint(name = "uk_appuser_username", columnNames = {"username"}),
    @UniqueConstraint(name = "uk_appuser_email", columnNames = {"email"})
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserEntity extends BaseEntity {

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false, unique = true, length = 50)
    private String username;

    @Column(nullable = false, unique = true, length = 100)
    private String email;

    @Column(nullable = false)
    private String password;
}
