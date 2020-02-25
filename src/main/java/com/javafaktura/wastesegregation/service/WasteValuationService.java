package com.javafaktura.wastesegregation.service;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Random;

//Service checking prices of waste on market
@Service
public class WasteValuationService implements WasteValuationInterface {

    private Random random = new Random();

    @Override
    public BigDecimal getPriceOfPlasticWasteType() {
        // Connecting to some external wastes prices provider
        return new BigDecimal(random.nextInt((1200 - 800) +1) + 800);
    }

    @Override
    public BigDecimal getPriceOfGlassWasteType() {
        // Connecting to some external wastes prices provider
        return new BigDecimal(random.nextInt((550 - 400) +1) + 800);
    }
}
