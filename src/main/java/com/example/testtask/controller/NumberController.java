package com.example.testtask.controller;

import com.example.testtask.dto.NumberCreateDto;
import com.example.testtask.dto.NumberViewDto;
import com.example.testtask.service.NumberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/numbers")
public class NumberController {

    private final NumberService service;

    @PostMapping
    public ResponseEntity<NumberViewDto> createNumber(@Valid @RequestBody NumberCreateDto createDto) {
        return ResponseEntity.ok(service.createNumber(createDto));
    }

}
