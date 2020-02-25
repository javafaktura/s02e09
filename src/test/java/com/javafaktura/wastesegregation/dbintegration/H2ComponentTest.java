package com.javafaktura.wastesegregation.dbintegration;

import com.javafaktura.wastesegregation.WasteSegregationApplication;
import com.javafaktura.wastesegregation.config.H2TestConfig;
import com.javafaktura.wastesegregation.model.Waste;
import com.javafaktura.wastesegregation.repository.WasteRepository;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;

import static com.javafaktura.wastesegregation.model.WasteType.GLASS;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {WasteSegregationApplication.class, H2TestConfig.class})
@ActiveProfiles("test")
public class H2ComponentTest {

    @Autowired
    private WasteRepository wasteRepository;

    @Test
    @DisplayName("Should find saved waste by id")
    public void should_findSavedWasteEntity() {
        //given
        Waste savedWaste = wasteRepository.save(new Waste(GLASS, new BigDecimal("1.50")));

        //when
        Waste foundWaste = wasteRepository.findById(savedWaste.getId()).orElse(null);

        //then
        assertThat(foundWaste).isNotNull();
        assertThat(foundWaste).isEqualTo(savedWaste);
    }

}
