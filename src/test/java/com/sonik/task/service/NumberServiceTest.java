package com.sonik.task.service;

import com.sonik.task.model.entity.NumberEntity;
import com.sonik.task.repository.NumberRepository;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class NumberServiceTest {

    @Test
    void addAndGetSortedTest() {
        NumberRepository repo = mock(NumberRepository.class);
        NumberService service = new NumberServiceImpl(repo);

        when(repo.findAllByOrderByValueAsc()).thenReturn(Arrays.asList(new NumberEntity(2), new NumberEntity(3)));
        when(repo.save(any(NumberEntity.class))).thenReturn(new NumberEntity(1));

        List<Integer> result = service.addNumber(1);
        assertThat(result).containsExactly(2, 3);

        verify(repo, times(1)).save(any(NumberEntity.class));
        verify(repo, times(1)).findAllByOrderByValueAsc();
    }
}
