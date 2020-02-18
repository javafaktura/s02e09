package com.javafaktura.wastesegregation.service;

import com.javafaktura.wastesegregation.model.Waste;
import com.javafaktura.wastesegregation.model.WasteBatch;
import com.javafaktura.wastesegregation.model.WasteType;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SegregationMetricsTest {

    @Test
    void shouldReturnRatioOfUnsortedWaste() {
        //given
        SegregationMetrics metrics = new SegregationMetrics();
        WasteBatch wasteBatch = new WasteBatch();
        wasteBatch.setWastes(createWasteList());

        //when
        BigDecimal result = metrics.getSegregationEfficiency(wasteBatch);

        //then
        assertEquals(new BigDecimal("0.50"), result);
    }

    private List<Waste> createWasteList() {
        var wastes = new ArrayList<Waste>();
        wastes.add(createWaste(WasteType.METAL_AND_PLASTICS, new BigDecimal("1")));
        wastes.add(createWaste(WasteType.OTHER_WASTE, new BigDecimal("1")));
        return wastes;
    }

    private Waste createWaste(WasteType type, BigDecimal weight) {
        var waste = new Waste();
        waste.setType(type);
        waste.setWeight(weight);
        return waste;
    }
}