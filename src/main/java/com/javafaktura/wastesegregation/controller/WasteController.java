package com.javafaktura.wastesegregation.controller;

import com.javafaktura.wastesegregation.model.Waste;
import com.javafaktura.wastesegregation.repository.WasteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

@Controller
public class WasteController {

    @Autowired
    private WasteRepository wasteRepository;

    @PostMapping(value = "/wastes/", consumes = "application/json", produces = "application/json")
    public Waste addWasteBatch(@Valid @RequestBody Waste waste) {
        return wasteRepository.save(waste);
    }
}
