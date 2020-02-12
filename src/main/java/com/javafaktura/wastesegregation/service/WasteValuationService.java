package com.javafaktura.wastesegregation.service;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Random;

@Service
public class WasteValuationService {

    private Random random = new Random();
    //Service checking prices of waste on market
    public BigDecimal getPriceOfPlasticWasteType() {
        return new BigDecimal(random.nextInt((1200 - 800) +1) + 800);
    }

    public BigDecimal getPriceOfGlassWasteType() {
        return new BigDecimal(random.nextInt((550 - 400) +1) + 800);
    }
}
