package com.mywallet.service.config;

import com.mywallet.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.userRepository.findByUsernameOrEmail(username)
            .map(user -> 
                User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .build()
            ).orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado: " + username));
    }
}
