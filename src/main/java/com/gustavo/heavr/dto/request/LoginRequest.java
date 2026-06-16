package com.gustavo.heavr.dto.request;

public record LoginRequest(
        String email,
        String password
) {}