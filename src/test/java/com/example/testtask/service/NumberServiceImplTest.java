package com.example.testtask.service;

import com.example.testtask.dto.NumberCreateDto;
import com.example.testtask.dto.NumberViewDto;
import com.example.testtask.entity.Number;
import com.example.testtask.mapper.NumberMapperImpl;
import com.example.testtask.repository.NumberRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class NumberServiceImplTest {

    @Mock
    private NumberRepository repository;

    @Spy
    private NumberMapperImpl mapper;

    @InjectMocks
    private NumberServiceImpl service;

    @Test
    void createNumberSuccess() {
        NumberCreateDto createDto = new NumberCreateDto(5);
        Number savedNumber = Number.builder().id(1L).value(5).build();
        List<Integer> sortedNumbers = List.of(1, 3, 5);

        doReturn(savedNumber).when(repository).save(any(Number.class));
        doReturn(sortedNumbers).when(repository).findAllSorted();

        NumberViewDto result = service.createNumber(createDto);

        assertNotNull(result);
        assertEquals(sortedNumbers, result.numbers());
        verify(repository).save(any(Number.class));
        verify(repository).findAllSorted();
    }

    @Test
    void createNumberRepositoryThrowsException() {
        NumberCreateDto createDto = new NumberCreateDto(10);

        doThrow(new RuntimeException("DB error")).when(repository).save(any(Number.class));

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            service.createNumber(createDto);
        });

        assertEquals("DB error", exception.getMessage());
        verify(repository).save(any(Number.class));
        verify(repository, never()).findAllSorted();
    }
}
