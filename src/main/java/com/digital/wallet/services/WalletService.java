package com.digital.wallet.services;

import com.digital.wallet.dtos.request.CreateWalletRequest;
import com.digital.wallet.dtos.response.CreateWalletResponse;
import com.digital.wallet.repositories.WalletRepository;
import com.digital.wallet.repositories.entities.Wallet;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

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
}
