package com.example.testtask.repository;

import com.example.testtask.entity.Number;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface NumberRepository extends JpaRepository<Number, Long> {

    @Query("SELECT n.value FROM Number n ORDER BY n.value")
    List<Integer> findAllSorted();

}
