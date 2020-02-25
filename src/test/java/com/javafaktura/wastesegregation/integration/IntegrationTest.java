package com.javafaktura.wastesegregation.integration;

import com.javafaktura.wastesegregation.model.Waste;
import com.javafaktura.wastesegregation.model.WasteBatch;
import com.javafaktura.wastesegregation.model.WasteType;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class IntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    @DisplayName("Should return waste price for get westprice request ")
    public void should_return_waste_price() {
        //when
        ResponseEntity<BigDecimal> response = restTemplate.getForEntity("/wasteprice/", BigDecimal.class);

        //then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    @DisplayName("Should find saved waste batch by id")
    public void should_find_saved_waste_batch_by_id() throws Exception {
        //given
        Waste plasticWaste = new Waste(WasteType.METAL_AND_PLASTICS, new BigDecimal("1.00"));
        Waste otherWaste = new Waste(WasteType.OTHER_WASTE, new BigDecimal("1.00"));
        List<Waste> wasteList = Arrays.asList(plasticWaste, otherWaste);

        HttpEntity<WasteBatch> entity = createWasteBatchHttpEntity(wasteList);

        ResponseEntity<WasteBatch> response = restTemplate.postForEntity("/waste_batch/", entity, WasteBatch.class);

        //when
        ResponseEntity<WasteBatch> getResponse = restTemplate.getForEntity("/waste_batch/?batchId=" + response.getBody().getId(), WasteBatch.class);

        //then
        assertThat(getResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(getResponse.getBody().getWastes().size()).isEqualTo(2);
        assertThat(getResponse.getBody().getWastes()).contains(plasticWaste);
        assertThat(getResponse.getBody().getWastes()).contains(otherWaste);
    }

    private HttpEntity<WasteBatch> createWasteBatchHttpEntity(List<Waste> wasteList) {
        WasteBatch waste = new WasteBatch();
        waste.setWastes(wasteList);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        return new HttpEntity<>(waste, headers);
    }

}
