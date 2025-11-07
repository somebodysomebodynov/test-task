package com.neverov.numbersorter.numbers.db;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface NumberRepository extends JpaRepository<NumberEntity, Long> {

	@Query("select n.value from NumberEntity n order by n.value asc")
	List<Double> findAllSortedAsc();
}
