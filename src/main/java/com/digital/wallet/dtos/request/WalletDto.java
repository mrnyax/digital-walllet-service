package com.digital.wallet.dtos.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record WalletDto(
        @NotBlank(message = "Name is required.")
        String name,
        @NotNull(message = "User ID is required")
        UUID userId
) {
}
