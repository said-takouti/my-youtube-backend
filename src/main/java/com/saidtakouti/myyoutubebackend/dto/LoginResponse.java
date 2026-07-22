package com.saidtakouti.myyoutubebackend.dto;

public record LoginResponse(Long id,
                            String username,
                            String email,
                            String token) {
}
