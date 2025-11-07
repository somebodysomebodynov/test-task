package com.sonik.task.repository;

import com.sonik.task.model.entity.NumberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NumberRepository extends JpaRepository<NumberEntity, Long> {
    List<NumberEntity> findAllByOrderByValueAsc();
}
