package com.tenstech.spring_security_reuse.config;

import com.tenstech.spring_security_reuse.user.JpaUserDetailsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Objects;
import java.util.Optional;

@Slf4j
@Configuration
public class AuditorAwareImpl implements AuditorAware<String> {

    @Autowired
    private JpaUserDetailsService userService;

    @Override
    public Optional<String> getCurrentAuditor() {
        try {
            String currentUsername = SecurityContextHolder.getContext().getAuthentication().getName();
            UserDetails optionalUser = userService.loadUserByUsername(currentUsername);
            return Objects.nonNull(optionalUser) ? Optional.of(currentUsername) : Optional.of("system");
        } catch (Exception e) {
            return Optional.of("System");
        }
    }
}