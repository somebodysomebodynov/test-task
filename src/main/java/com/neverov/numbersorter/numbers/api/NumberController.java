package com.neverov.numbersorter.numbers.api;

import com.neverov.numbersorter.numbers.domain.NumberService;
import jakarta.validation.Valid;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/numbers")
public class NumberController {
	private final NumberService numberService;
	private final Logger log = LoggerFactory.getLogger(NumberController.class);
	public NumberController(NumberService numberService) {
		this.numberService = numberService;
	}

	@PostMapping
	public ResponseEntity<NumbersListResponse> addNumber(
			@RequestBody @Valid NumberRequest request
	) {
		log.info("Adding number: {}", request.value());

		List<Double> numbers = numberService.createNumber(request.value());
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(new NumbersListResponse(numbers));
	}

}
