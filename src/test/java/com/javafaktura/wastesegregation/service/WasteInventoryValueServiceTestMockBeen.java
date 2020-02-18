package com.javafaktura.wastesegregation.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest
class WasteInventoryValueServiceTestMockBeen {

    @Autowired
    private WasteInventoryValueService wasteInventoryValueService;

    @MockBean
    private WasteValuationService wasteValuationServiceMock;

    @Test
    public void should_returnValue_OfWasteInInventory() {
        //given
        when(wasteValuationServiceMock.getPriceOfPlasticWasteType()).thenReturn(new BigDecimal("1000"));

        //when
        BigDecimal result = wasteInventoryValueService.getValeForPlasticWastesInInventory();

        //then
        assertThat(result).isEqualTo(new BigDecimal("700.0"));
    }

}