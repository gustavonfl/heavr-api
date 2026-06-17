package com.gustavo.heavr.dto.response;

public record AuthResponse(
        String token,
        String name,
        String email
) {}