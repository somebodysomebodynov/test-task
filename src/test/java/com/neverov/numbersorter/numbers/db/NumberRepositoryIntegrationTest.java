package com.neverov.numbersorter.numbers.db;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Testcontainers
@Transactional
class NumberRepositoryIntegrationTest {

	@Container
	static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:13")
			.withDatabaseName("testdb")
			.withUsername("test")
			.withPassword("test");

	@DynamicPropertySource
	static void configureProperties(DynamicPropertyRegistry registry) {
		registry.add("spring.datasource.url", postgres::getJdbcUrl);
		registry.add("spring.datasource.username", postgres::getUsername);
		registry.add("spring.datasource.password", postgres::getPassword);
		registry.add("spring.jpa.hibernate.ddl-auto", () -> "create-drop");
		registry.add("spring.jpa.database-platform", () -> "org.hibernate.dialect.PostgreSQLDialect");
	}

	@Autowired
	private NumberRepository numberRepository;

	@Test
	void shouldSaveAndFindAllNumbersSorted() {
		// Given
		NumberEntity entity1 = new NumberEntity(3.0);
		NumberEntity entity2 = new NumberEntity(1.0);
		NumberEntity entity3 = new NumberEntity(2.0);

		// When
		numberRepository.save(entity1);
		numberRepository.save(entity2);
		numberRepository.save(entity3);

		List<Double> result = numberRepository.findAllSortedAsc();

		// Then
		assertThat(result).containsExactly(1.0, 2.0, 3.0);
	}
}