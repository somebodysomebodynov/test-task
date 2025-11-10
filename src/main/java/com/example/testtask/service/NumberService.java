package com.example.testtask.service;

import com.example.testtask.dto.NumberCreateDto;
import com.example.testtask.dto.NumberViewDto;

public interface NumberService {
    NumberViewDto createNumber(NumberCreateDto createDto);
}
