package com.saidtakouti.myyoutubebackend.service;

import com.saidtakouti.myyoutubebackend.dto.LoginRequest;
import com.saidtakouti.myyoutubebackend.dto.RegisterUserRequest;
import com.saidtakouti.myyoutubebackend.entity.AppUser;
import com.saidtakouti.myyoutubebackend.exception.InvalidCredentialsException;
import com.saidtakouti.myyoutubebackend.exception.UsernameAlreadyExistsException;
import com.saidtakouti.myyoutubebackend.repository.AppUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AppUserService {

    private final AppUserRepository appUserRepository;
    private final PasswordEncoder passwordEncoder;

    public AppUser register(RegisterUserRequest request) {

        if (appUserRepository.findByUsername(request.username()).isPresent()) {
            throw new UsernameAlreadyExistsException("Username already exists!");
        }
        AppUser appUser = new AppUser();
        appUser.setUsername(request.username());
        appUser.setEmail(request.email());
        appUser.setPasswordHash(passwordEncoder.encode(request.password()));

        return appUserRepository.save(appUser);
    }

    public AppUser login(LoginRequest request) {
        AppUser user = appUserRepository.findByUsername(request.username()).orElseThrow(
                () -> new InvalidCredentialsException("Invalid username or password")
        );
        if (!passwordEncoder.matches(request.password(), user.getPasswordHash())) {
            throw new InvalidCredentialsException("Invalid username or password");
        }
        return user;
    }
}
