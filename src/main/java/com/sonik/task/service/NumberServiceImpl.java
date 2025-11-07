package com.sonik.task.service;

import com.sonik.task.model.entity.NumberEntity;
import com.sonik.task.repository.NumberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NumberServiceImpl implements NumberService{

    private final NumberRepository repository;

    @Override
    public List<Integer> addNumber(Integer number) {
        repository.save(new NumberEntity(number));
        return repository.findAllByOrderByValueAsc().stream()
                .map(NumberEntity::getValue)
                .toList();
    }
}
