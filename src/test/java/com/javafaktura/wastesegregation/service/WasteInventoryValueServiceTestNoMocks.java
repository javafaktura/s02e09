package com.javafaktura.wastesegregation.service;

import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

class WasteInventoryValueServiceTestNoMocks {

    @Test
    @DisplayName("should return value of waste in inventory for given price")
    public void should_returnValue_OfWasteInInventory() {
        //given
        WasteValuationInterface wasteValuationServiceStub
                = createWasteValuationServiceStubReturningPlasticWasteValue(new BigDecimal(1000));

        WasteInventoryValueService wasteInventoryValueService
                = new WasteInventoryValueService(wasteValuationServiceStub);

        //when
        BigDecimal result = wasteInventoryValueService.getValeForPlasticWastesInInventory();

        //then
        assertThat(result).isEqualTo(new BigDecimal("700.0"));
    }

    @NotNull
    private WasteValuationInterface createWasteValuationServiceStubReturningPlasticWasteValue(BigDecimal value) {
        return new WasteValuationInterface() {
            @Override
            public BigDecimal getPriceOfPlasticWasteType() {
                return value;
            }

            @Override
            public BigDecimal getPriceOfGlassWasteType() {
                return null;
            }
        };
    }

}