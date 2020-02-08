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

/*
    @PostMapping("/questions/{questionId}/answers")
    public WasteBatch addAnswer(@PathVariable Long questionId,
                                @Valid @RequestBody WasteBatch wasteBatch) {
        return wasteBatchRepository.findById(questionId)
                .map(question -> {
                    wasteBatch.setQuestion(question);
                    return answerRepository.save(wasteBatch);
                }).orElseThrow(() -> new ResourceNotFoundException("Question not found with id " + questionId));
    }

    @PutMapping("/questions/{questionId}/answers/{answerId}")
    public WasteBatch updateAnswer(@PathVariable Long questionId,
                                   @PathVariable Long answerId,
                                   @Valid @RequestBody WasteBatch wasteBatchRequest) {
        if(!wasteBatchRepository.existsById(questionId)) {
            throw new ResourceNotFoundException("Question not found with id " + questionId);
        }

        return answerRepository.findById(answerId)
                .map(wasteBatch -> {
                    wasteBatch.setText(wasteBatchRequest.getText());
                    return answerRepository.save(wasteBatch);
                }).orElseThrow(() -> new ResourceNotFoundException("Answer not found with id " + answerId));
    }

    @DeleteMapping("/questions/{questionId}/answers/{answerId}")
    public ResponseEntity<?> deleteAnswer(@PathVariable Long questionId,
                                          @PathVariable Long answerId) {
        if(!wasteBatchRepository.existsById(questionId)) {
            throw new ResourceNotFoundException("Question not found with id " + questionId);
        }

        return answerRepository.findById(answerId)
                .map(wasteBatch -> {
                    answerRepository.delete(wasteBatch);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException("Answer not found with id " + answerId));

    }*/
}