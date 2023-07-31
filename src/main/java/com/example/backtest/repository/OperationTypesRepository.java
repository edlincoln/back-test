package com.example.backtest.repository;

import com.example.backtest.entity.OperationTypes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OperationTypesRepository extends JpaRepository<OperationTypes, Long> {

}