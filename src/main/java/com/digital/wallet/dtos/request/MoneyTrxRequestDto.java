package com.digital.wallet.dtos.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record MoneyTrxRequestDto(
        @Min(1) long amount,
        @NotBlank String currency,
        String externalRef
) {
}
