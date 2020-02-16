package com.javafaktura.wastesegregation.service;

import com.javafaktura.wastesegregation.model.Waste;
import com.javafaktura.wastesegregation.model.WasteType;
import com.javafaktura.wastesegregation.repository.WasteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

import static java.math.RoundingMode.CEILING;

@Service
public class WasteInventoryValueService {

    private static final int SEGREGATION_EFFICIENCY_FACTOR = 70;

    @Autowired
    private WasteValuationService wasteValuationService;

    public BigDecimal getValeForPlasticWastesInInventory() {

        return wasteValuationService.getPriceOfPlasticWasteType()
                .divide(new BigDecimal(SEGREGATION_EFFICIENCY_FACTOR), 2, CEILING);
    }

}
