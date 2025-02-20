package com.d3xtro.GastoTracker.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.d3xtro.GastoTracker.Models.Income;


@Repository
public interface IncomeRepo extends JpaRepository<Income, Long> {
    List<Income> findByUserId(Long userId);
    List<Income> findByCategory(String category);
}
