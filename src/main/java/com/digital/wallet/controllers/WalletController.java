package com.digital.wallet.controllers;

import com.digital.wallet.dtos.request.CreateWalletRequest;
import com.digital.wallet.dtos.response.CreateWalletResponse;
import com.digital.wallet.services.WalletService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("wallets")
public class WalletController {

    private final WalletService walletService;

    public WalletController(WalletService walletService) {
        this.walletService = walletService;
    }

    @PostMapping
    public ResponseEntity<CreateWalletResponse> create(@RequestBody @Valid CreateWalletRequest createWalletRequest) {
        final CreateWalletResponse response = walletService.createWallet(createWalletRequest);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }
}
