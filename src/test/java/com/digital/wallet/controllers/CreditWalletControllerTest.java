package com.digital.wallet.controllers;

import com.digital.wallet.dtos.request.CreateWalletRequest;
import com.digital.wallet.dtos.request.MoneyTrxRequestDto;
import com.digital.wallet.dtos.response.CreateWalletResponse;
import com.digital.wallet.dtos.response.MoneyTrxResponseDto;
import com.digital.wallet.services.WalletService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(WalletController.class)
class CreditWalletControllerTest {
    private static final String WALLET_ENDPOINT = "/wallets";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private WalletService walletService;

    @Test
    void creditWallet_returnsStatusOk() throws Exception {

        final UUID userId = UUID.randomUUID();
        when(walletService.credit(userId, mock(MoneyTrxRequestDto.class)))
                .thenReturn(new MoneyTrxResponseDto());

        final MoneyTrxRequestDto req = new MoneyTrxRequestDto(100, "KES", "KXX");

        // when / then
        mockMvc.perform(post("/wallets/{id}/credit", userId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(req)))
                .andExpect(status().isOk());

    }



}