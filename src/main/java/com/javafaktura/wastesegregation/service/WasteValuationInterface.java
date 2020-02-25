package com.javafaktura.wastesegregation.service;

import java.math.BigDecimal;

public interface WasteValuationInterface {

    BigDecimal getPriceOfPlasticWasteType();
    BigDecimal getPriceOfGlassWasteType();

}
