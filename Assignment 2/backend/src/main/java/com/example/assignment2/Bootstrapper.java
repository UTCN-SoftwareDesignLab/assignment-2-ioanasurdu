package com.example.assignment2;

import com.example.assignment2.book.repository.BookRepository;
import com.example.assignment2.security.service.AuthService;
import com.example.assignment2.security.dto.SignupRequest;
import com.example.assignment2.user.repository.RoleRepository;
import com.example.assignment2.user.repository.UserRepository;
import com.example.assignment2.user.model.ERole;
import com.example.assignment2.user.model.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@RequiredArgsConstructor
public class Bootstrapper implements ApplicationListener<ApplicationReadyEvent> {

    private final RoleRepository roleRepository;

    private final UserRepository userRepository;

    private final AuthService authService;

    private final BookRepository bookRepository;

    @Value("${app.bootstrap}")
    private Boolean bootstrap;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        if (bootstrap) {
            bookRepository.deleteAll();
            userRepository.deleteAll();
            roleRepository.deleteAll();
            for (ERole value : ERole.values()) {
                roleRepository.save(
                        Role.builder()
                                .name(value)
                                .build()
                );
            }
            authService.register(SignupRequest.builder()
                    .email("admin@email.com")
                    .username("admin")
                    .password("admin1!")
                    .roles(Set.of("ADMIN"))
                    .build());
            authService.register(SignupRequest.builder()
                    .email("ioana@email.com")
                    .username("ioana")
                    .password("ioana19!")
                    .roles(Set.of("EMPLOYEE"))
                    .build());
        }
    }
}

