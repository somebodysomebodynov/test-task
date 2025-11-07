package com.neverov.numbersorter.numbers.api;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;

public record NumberRequest(
		@NotNull(message = "Value cannot be null")
		@JsonFormat(shape = JsonFormat.Shape.NUMBER)
		Double value
){}