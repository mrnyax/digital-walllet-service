package com.digital.wallet.services;

import com.digital.wallet.dtos.request.CreateWalletRequest;
import com.digital.wallet.dtos.request.MoneyTrxRequestDto;
import com.digital.wallet.dtos.response.CreateWalletResponse;
import com.digital.wallet.dtos.response.MoneyTrxResponseDto;
import com.digital.wallet.repositories.WalletRepository;
import com.digital.wallet.repositories.entities.Wallet;
import jakarta.validation.Valid;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class WalletService {

    private final WalletRepository walletRepository;

    public WalletService(final WalletRepository walletRepository) {
        this.walletRepository = walletRepository;
    }

    @Transactional
    public CreateWalletResponse createWallet(final CreateWalletRequest createWalletRequest) {
        final Wallet wallet = Wallet.of(createWalletRequest.userId(), createWalletRequest.name());
        final Wallet saved = walletRepository.save(wallet);
        return new CreateWalletResponse(saved.getOwner());
    }

    @Transactional
    public MoneyTrxResponseDto credit(UUID id, @Valid MoneyTrxRequestDto req) {
        return new MoneyTrxResponseDto();
    }
}
