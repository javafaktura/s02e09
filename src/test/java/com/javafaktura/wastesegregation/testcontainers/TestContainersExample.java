package com.javafaktura.wastesegregation.testcontainers;

import com.javafaktura.wastesegregation.model.Waste;
import com.javafaktura.wastesegregation.repository.WasteRepository;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.testcontainers.containers.PostgreSQLContainer;

import java.math.BigDecimal;

import static com.javafaktura.wastesegregation.model.WasteType.BIODEGRADABLE_WASTE;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(initializers = {TestContainersExample.Initializer.class})
public class TestContainersExample {

    @ClassRule
    public static PostgreSQLContainer postgreSQLContainer = new PostgreSQLContainer("postgres:11.1")
            .withDatabaseName("integration-tests-db")
            .withUsername("sa")
            .withPassword("sa");

    static class Initializer
            implements ApplicationContextInitializer<ConfigurableApplicationContext> {
        public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
            TestPropertyValues.of(
                    "spring.datasource.url=" + postgreSQLContainer.getJdbcUrl(),
                    "spring.datasource.username=" + postgreSQLContainer.getUsername(),
                    "spring.datasource.password=" + postgreSQLContainer.getPassword()
            ).applyTo(configurableApplicationContext.getEnvironment());
        }
    }

    @Autowired
    private WasteRepository wasteRepository;

    @Test
    @DisplayName("Should find saved waste by id")
    public void should_findSavedWasteEntity() {
        //given
        Waste savedWaste = wasteRepository.save(new Waste(BIODEGRADABLE_WASTE, new BigDecimal("4.00")));

        //when
        Waste foundWaste = wasteRepository.findById(savedWaste.getId()).orElse(null);

        //then
        assertThat(foundWaste).isNotNull();
        assertThat(foundWaste).isEqualTo(savedWaste);
    }
}