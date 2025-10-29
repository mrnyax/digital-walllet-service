package com.digital.wallet.dtos.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record WalletDto(
        @NotNull(message = "User ID is required")
        UUID userId,
        @NotBlank(message = "Name is required.")
        String name
) {
}
