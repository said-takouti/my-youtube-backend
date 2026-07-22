package com.saidtakouti.myyoutubebackend.controller;

import com.saidtakouti.myyoutubebackend.dto.LoginRequest;
import com.saidtakouti.myyoutubebackend.dto.LoginResponse;
import com.saidtakouti.myyoutubebackend.dto.RegisterUserRequest;
import com.saidtakouti.myyoutubebackend.dto.UserResponse;
import com.saidtakouti.myyoutubebackend.entity.AppUser;
import com.saidtakouti.myyoutubebackend.service.AppUserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AppUserService appUserService;

    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(@Valid @RequestBody RegisterUserRequest request) {
        AppUser registeredUser = appUserService.register(request);
        UserResponse response = new UserResponse(registeredUser.getId(), registeredUser.getUsername(), registeredUser.getEmail());
        return ResponseEntity.status(HttpStatus.CREATED).
                body(response);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest request) {
        AppUser user = appUserService.login(request);
        LoginResponse loginResponse = new LoginResponse(user.getId(), user.getUsername(), user.getEmail(),"temporary-token");
        return ResponseEntity.ok(loginResponse);
    }


}
