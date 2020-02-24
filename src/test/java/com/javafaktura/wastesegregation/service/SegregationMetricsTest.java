package com.javafaktura.wastesegregation.service;

import com.javafaktura.wastesegregation.model.Waste;
import com.javafaktura.wastesegregation.model.WasteBatch;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static com.javafaktura.wastesegregation.model.WasteType.METAL_AND_PLASTICS;
import static com.javafaktura.wastesegregation.model.WasteType.OTHER_WASTE;
import static org.junit.jupiter.api.Assertions.assertEquals;

class SegregationMetricsTest {

    @Test
    @DisplayName("Should return ratio of unsorted waste")
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
        wastes.add(new Waste(METAL_AND_PLASTICS, new BigDecimal("1")));
        wastes.add(new Waste(OTHER_WASTE, new BigDecimal("1")));
        return wastes;
    }

}