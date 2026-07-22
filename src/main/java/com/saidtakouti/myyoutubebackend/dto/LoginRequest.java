package com.saidtakouti.myyoutubebackend.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record LoginRequest(@NotBlank @Size(min = 3, max = 50) String username,
                           @NotBlank @Size(max = 255) String password) {
}
