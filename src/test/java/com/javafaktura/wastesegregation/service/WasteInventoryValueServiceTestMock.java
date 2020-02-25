package com.javafaktura.wastesegregation.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class WasteInventoryValueServiceTestMock {

    @InjectMocks
    private WasteInventoryValueService wasteInventoryValueService;

    @Mock
    WasteValuationService wasteValuationServiceMock;

    @Test
    @DisplayName("should return value of waste in inventory for given price")
    public void should_returnValue_OfWasteInInventory() {
        //given
        when(wasteValuationServiceMock.getPriceOfPlasticWasteType()).thenReturn(new BigDecimal("1000"));

        //when
        BigDecimal result = wasteInventoryValueService.getValeForPlasticWastesInInventory();

        //then
        assertThat(result).isEqualTo(new BigDecimal("700.0"));
    }

}