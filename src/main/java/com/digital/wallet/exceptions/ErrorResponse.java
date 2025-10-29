package com.digital.wallet.exceptions;

import java.util.List;

public record ErrorResponse(
        List<String> errors,
        int statusCode
) {
}
