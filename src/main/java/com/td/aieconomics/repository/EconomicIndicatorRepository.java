package com.td.aieconomics.repository;

import com.td.aieconomics.entity.EconomicIndicator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EconomicIndicatorRepository extends JpaRepository<EconomicIndicator, Long> {
}
