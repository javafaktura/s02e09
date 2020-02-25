package com.javafaktura.wastesegregation.service;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class WasteInventoryValueService {

    private static final BigDecimal SEGREGATION_EFFICIENCY_FACTOR = new BigDecimal("0.7");

    private WasteValuationInterface wasteValuationService;

    public WasteInventoryValueService(WasteValuationInterface wasteValuationService) {
        this.wasteValuationService = wasteValuationService;
    }

    public BigDecimal getValeForPlasticWastesInInventory() {

        return wasteValuationService.getPriceOfPlasticWasteType()
                .multiply(SEGREGATION_EFFICIENCY_FACTOR);
    }

}
