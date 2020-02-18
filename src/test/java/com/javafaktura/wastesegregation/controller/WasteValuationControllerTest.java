package com.javafaktura.wastesegregation.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.javafaktura.wastesegregation.service.WasteValuationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

@WebMvcTest(WasteValuationController.class)
class WasteValuationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private WasteValuationService service;

    @Test
    public void should_return_price_waste() throws Exception {
        when(service.getPriceOfPlasticWasteType()).thenReturn(BigDecimal.ZERO);
        this.mockMvc.perform(get("/wasteprice")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string("0"));
    }

}