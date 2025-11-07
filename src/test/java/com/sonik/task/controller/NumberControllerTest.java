package com.sonik.task.controller;

import com.sonik.task.model.api.NumberRequest;
import com.sonik.task.repository.NumberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
class NumberControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private NumberRepository repository;

    @Container
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:15")
            .withDatabaseName("numbers_test")
            .withUsername("test")
            .withPassword("test");

    @DynamicPropertySource
    static void configure(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgres::getJdbcUrl);
        registry.add("spring.datasource.username", postgres::getUsername);
        registry.add("spring.datasource.password", postgres::getPassword);
    }

    @BeforeEach
    void setUp() {
        repository.deleteAll();
    }

    @Test
    void postNumbers_shouldReturnSortedList() {
        ResponseEntity<Integer[]> response1 = restTemplate.postForEntity(
                "http://localhost:" + port + "/numbers",
                new NumberRequest(3),
                Integer[].class
        );
        assertThat(response1.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response1.getBody()).containsExactly(3);

        ResponseEntity<Integer[]> response2 = restTemplate.postForEntity(
                "http://localhost:" + port + "/numbers",
                new NumberRequest(2),
                Integer[].class
        );
        assertThat(response2.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response2.getBody()).containsExactly(2, 3);

        ResponseEntity<Integer[]> response3 = restTemplate.postForEntity(
                "http://localhost:" + port + "/numbers",
                new NumberRequest(1),
                Integer[].class
        );
        assertThat(response3.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response3.getBody()).containsExactly(1, 2, 3);
    }

    @Test
    void postNullNumber_shouldReturnBadRequest() {
        ResponseEntity<String> response = restTemplate.postForEntity(
                "http://localhost:" + port + "/numbers",
                new NumberRequest(null),
                String.class
        );
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(response.getBody()).isEqualTo("Число не может быть null");
    }
}