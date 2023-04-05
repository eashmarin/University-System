package ru.nsu.fit.universitysystem;

import org.jetbrains.annotations.NotNull;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.Map;

public class TestContainerDbContextInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

    private static final PostgreSQLContainer<?> pgContainer = new PostgreSQLContainer<>("postgres:15-alpine")
            .withExposedPorts(5432);
    //.withReuse(true)
    //.withInitScript("db/-db-init.sql")

    static {
        pgContainer.start();
    }

    @Override
    public void initialize(@NotNull ConfigurableApplicationContext applicationContext) {
        overrideProperties(applicationContext, Map.ofEntries(
                Map.entry("spring.datasource.url", pgContainer.getJdbcUrl()),
                Map.entry("spring.datasource.username", pgContainer.getUsername()),
                Map.entry("spring.datasource.password", pgContainer.getPassword())
        ));
    }

    private void overrideProperties(ConfigurableApplicationContext applicationContext, Map<String, String> properties) {
        TestPropertyValues
                .of(properties)
                .applyTo(applicationContext);
    }
}
