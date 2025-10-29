package com.digital.wallet.repositories;

import com.digital.wallet.repositories.entities.Wallet;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class WalletRepositoryTest extends PostgresContainerConfig {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private WalletRepository walletRepository;

    @Test
    void saveWallet() {
        // Arrange
        final UUID userId = UUID.randomUUID();
        final String name = "test user";
        final Wallet wallet = Wallet.of(userId, name);
        entityManager.persistAndFlush(wallet);

        // Act
        final Optional<Wallet> result = walletRepository.findById(userId);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(name, result.get().getOwner());
        assertEquals(userId, result.get().getId());
        assertEquals(0, result.get().getVersion());
        assertNotNull(result.get().getId());
    }

}