package com.javafaktura.wastesegregation.controller;

import com.javafaktura.wastesegregation.model.WasteBatch;
import com.javafaktura.wastesegregation.repository.WasteBatchRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class SegregationFacilityController {
    private static final Logger LOG = LoggerFactory.getLogger(SegregationFacilityController.class);

    @Autowired
    private WasteBatchRepository wasteBatchRepository;

    @GetMapping("/waste_batch/{batch_id}")
    public ResponseEntity<WasteBatch> getWasteBatchById(@RequestParam Long batchId) {
        var wasteBatch = wasteBatchRepository.findById(batchId)
                .orElseThrow(() -> new ResourceNotFoundException("WasteBatch not found with id " + batchId));
        LOG.debug("wasteBatch: [{}]", wasteBatch);
        return ResponseEntity.ok().body(wasteBatch);
    }

    @PostMapping("/waste_batch/")
    public WasteBatch addWasteBatch(@Valid @RequestBody WasteBatch wasteBatch) {
        LOG.debug("addWasteBatch: [{}]", wasteBatch);

        for (var waste : wasteBatch.getWastes()) {
            waste.setWasteBatch(wasteBatch);
        }

        wasteBatch = wasteBatchRepository.save(wasteBatch);
        return wasteBatch;
    }

}