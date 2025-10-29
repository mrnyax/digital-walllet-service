package com.digital.wallet.controllers;

import com.digital.wallet.dtos.request.WalletDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(WalletController.class)
class WalletControllerTest {
    private static final String WALLET_ENDPOINT = "/wallets";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void createWallet_returnsStatusCreated() throws Exception {
        final UUID userId = UUID.randomUUID();
        final String walletName = "Test Wallet";
        final WalletDto walletDto = new WalletDto(userId, walletName);
        mockMvc.perform(
                        post(WALLET_ENDPOINT)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(walletDto))
                )
                .andExpect(status().isCreated());
    }

    @Test
    void createWallet_whenInvalidRequestBody_returnsBadRequest() throws Exception {
        final WalletDto walletDto = new WalletDto(null, null);
        mockMvc.perform(
                        post(WALLET_ENDPOINT)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(walletDto))
                )
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.errors").exists())
                .andExpect(jsonPath("$.statusCode").exists())
        ;
    }

}