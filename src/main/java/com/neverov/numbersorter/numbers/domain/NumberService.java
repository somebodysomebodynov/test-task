package com.neverov.numbersorter.numbers.domain;

import com.neverov.numbersorter.numbers.db.NumberEntity;
import com.neverov.numbersorter.numbers.db.NumberRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class NumberService {
	private final NumberRepository repository;
	public NumberService(NumberRepository repository) {
		this.repository = repository;
	}
	@Transactional
	public List<Double> createNumber(Double value) {
		var entity = new NumberEntity(value);
		repository.save(entity);
		return repository.findAllSortedAsc();
	}
}
