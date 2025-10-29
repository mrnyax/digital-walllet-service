package com.digital.wallet.controllers;

import com.digital.wallet.dtos.request.WalletDto;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("wallets")
public class WalletController {

    @PostMapping
    public ResponseEntity<?> create(@RequestBody @Valid WalletDto request) {
        return ResponseEntity
                .created(URI.create("/api/v1/wallet"))
                .body(request);
    }
}
