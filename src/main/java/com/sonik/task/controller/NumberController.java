package com.sonik.task.controller;

import com.sonik.task.exception.InvalidNumberException;
import com.sonik.task.model.api.NumberRequest;
import com.sonik.task.model.entity.NumberEntity;
import com.sonik.task.repository.NumberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/numbers")
@RequiredArgsConstructor
public class NumberController {

    private final NumberRepository repository;

    @PostMapping
    public ResponseEntity<List<Integer>> addNumber(@RequestBody NumberRequest request) {
        if (request.number() == null) {
            throw new InvalidNumberException("Число не может быть null");
        }

        NumberEntity numberEntity = new NumberEntity(request.number());
        repository.save(numberEntity);

        List<Integer> sortedNumbers = repository.findAll()
                .stream()
                .map(NumberEntity::getValue)
                .sorted()
                .collect(Collectors.toList());

        return ResponseEntity.ok(sortedNumbers);
    }

    @ExceptionHandler(InvalidNumberException.class)
    public ResponseEntity<String> handleInvalidNumber(InvalidNumberException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
}
