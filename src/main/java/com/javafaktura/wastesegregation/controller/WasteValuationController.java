package com.javafaktura.wastesegregation.controller;

import com.javafaktura.wastesegregation.service.WasteValuationService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;

@Controller
public class WasteValuationController {

    private final WasteValuationService service;

    public WasteValuationController(WasteValuationService service) {
        this.service = service;
    }

    @GetMapping("/wasteprice")
    public @ResponseBody
    BigDecimal getWastePrice() {
        return service.getPriceOfPlasticWasteType();
    }
}
