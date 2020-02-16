package com.javafaktura.wastesegregation.integration;

import com.javafaktura.wastesegregation.model.Waste;
import com.javafaktura.wastesegregation.model.WasteBatch;
import com.javafaktura.wastesegregation.model.WasteType;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.math.BigDecimal;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class IntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void should_return_waste_price(){
        //when
        ResponseEntity<BigDecimal> response = restTemplate.getForEntity("/wasteprice/", BigDecimal.class);

        //then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

    }

    @Test
    public void should_return_not_found_response() throws Exception {
        //given
        WasteBatch waste = new WasteBatch();
        waste.setWastes(Collections.emptyList());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<WasteBatch> entity = new HttpEntity<>(waste, headers);

        ResponseEntity<WasteBatch> response = restTemplate.postForEntity("/waste_batch/",entity, WasteBatch.class);

        //when
        ResponseEntity<WasteBatch> getResponse = restTemplate.getForEntity("/waste_batch/?batchId=" + response.getBody().getId(), WasteBatch.class);

        //then
        assertThat(getResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
    }
}
