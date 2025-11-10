package com.example.testtask.dto;

import jakarta.validation.constraints.NotNull;

public record NumberCreateDto (
        @NotNull
        Integer value
) {
}
