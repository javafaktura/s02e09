package com.javafaktura.wastesegregation.service;

import com.javafaktura.wastesegregation.model.Waste;
import com.javafaktura.wastesegregation.model.WasteBatch;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.function.Predicate;

import static com.javafaktura.wastesegregation.model.WasteType.OTHER_WASTE;
import static java.math.BigDecimal.ZERO;
import static java.math.RoundingMode.CEILING;

@Service
public class SegregationMetrics {

    public BigDecimal getSegregationEfficiency(WasteBatch wasteBatch) {
        BigDecimal segregatedWastes =
                getSumOfFilteredWastes(wasteBatch, waste -> waste.getType() != OTHER_WASTE);
        BigDecimal total =
                getSumOfFilteredWastes(wasteBatch, waste -> true);

        return segregatedWastes.divide(total, 2, CEILING);
    }

    private BigDecimal getSumOfFilteredWastes(WasteBatch wasteBatch, Predicate<Waste> wastePredicate) {
        return wasteBatch.getWastes().stream()
                .filter(wastePredicate)
                .map(Waste::getWeight)
                .reduce(ZERO, BigDecimal::add);
    }
}
