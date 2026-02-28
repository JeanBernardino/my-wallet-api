package com.mywallet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.mywallet.entity.user.UserEntity;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    
    @Query("""
        SELECT
            user
        FROM 
            UserEntity user
        WHERE 
            user.username = :username OR user.email = :username
    """)
    Optional<UserEntity> findByUsernameOrEmail(String username);
    
    Optional<UserEntity> findByEmail(String email);
    
    boolean existsByUsername(String username);
    
    boolean existsByEmail(String email);
}
