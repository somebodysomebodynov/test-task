package com.example.testtask.service;

import com.example.testtask.dto.NumberCreateDto;
import com.example.testtask.dto.NumberViewDto;
import com.example.testtask.mapper.NumberMapper;
import com.example.testtask.repository.NumberRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class NumberServiceImpl implements NumberService {

    private final NumberRepository repository;
    private final NumberMapper mapper;

    @Override
    @Transactional
    public NumberViewDto createNumber(NumberCreateDto createDto) {
        log.info("Создание нового числа: {}", createDto.value());
        repository.save(mapper.toEntity(createDto));
        List<Integer> sortedNumbers =  repository.findAllSorted();
        return new NumberViewDto(sortedNumbers);
    }
}
