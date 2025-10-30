package com.digital.wallet.services;

import com.digital.wallet.dtos.request.CreateWalletRequest;
import com.digital.wallet.dtos.response.CreateWalletResponse;
import com.digital.wallet.repositories.WalletRepository;
import com.digital.wallet.repositories.entities.Wallet;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class WalletServiceTest {

    @InjectMocks
    private WalletService walletService;

    @Mock
    private WalletRepository walletRepository;

    @Test
    void createWallet() {
        final UUID userId = UUID.randomUUID();
        final Wallet wallet = Wallet.of(userId, "test user");
        when(walletRepository.save(any(Wallet.class))).thenReturn(wallet);

        final CreateWalletRequest request = new CreateWalletRequest(userId, "test user");
        final CreateWalletResponse response = walletService.createWallet(request);

        assertEquals("test user", response.name());
    }

}