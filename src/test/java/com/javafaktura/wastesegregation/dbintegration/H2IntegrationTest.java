package com.javafaktura.wastesegregation.dbintegration;

import com.javafaktura.wastesegregation.WasteSegregationApplication;
import com.javafaktura.wastesegregation.config.H2TestConfig;
import com.javafaktura.wastesegregation.model.Waste;
import com.javafaktura.wastesegregation.repository.WasteRepository;
import javafx.application.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = { WasteSegregationApplication.class, H2TestConfig.class })
public class H2IntegrationTest {

    @Autowired
    private WasteRepository wasteRepository;

    @Test
    public void should_findSavedWasteEntity(){
        //given
        Waste savedWaste = wasteRepository.save(new Waste());

        //when
        Waste foundWaste = wasteRepository.findById(savedWaste.getId()).orElse(null);

        //then
        assertThat(foundWaste).isNotNull();
        assertThat(foundWaste).isEqualTo(savedWaste);
    }
}
