package com.gustavo.heavr.dto.request.response;

public record AuthResponse(
        String token,
        String name,
        String email
) {}