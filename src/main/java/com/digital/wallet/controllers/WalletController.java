package com.digital.wallet.controllers;

import com.digital.wallet.dtos.request.CreateWalletRequest;
import com.digital.wallet.dtos.request.MoneyTrxRequestDto;
import com.digital.wallet.dtos.response.CreateWalletResponse;
import com.digital.wallet.dtos.response.MoneyTrxResponseDto;
import com.digital.wallet.services.WalletService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.UUID;

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

    @PostMapping("/{id}/credit")
    public ResponseEntity<MoneyTrxResponseDto> credit(@PathVariable UUID id,
                                                      @RequestHeader(value="Idempotency-Key", required=false) String key,
                                                      @Valid @RequestBody MoneyTrxRequestDto req) {
        final MoneyTrxResponseDto response = walletService.credit(id, req);
        return ResponseEntity.ok(response);
    }
}
