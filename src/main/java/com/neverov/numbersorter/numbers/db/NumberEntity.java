package com.neverov.numbersorter.numbers.db;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Entity
@Getter
@NoArgsConstructor
@Table(name = "numbers", indexes = {
		@Index(name = "idx_value_btree", columnList = "value")
})
public class NumberEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "value", unique = false, nullable = false)
	private Double value;

	@Column(name = "created_at", nullable = false)
	private LocalDateTime createdAt;

	public NumberEntity(Double value) {
		this.value = value;
		this.createdAt = LocalDateTime.now();
	}
}
