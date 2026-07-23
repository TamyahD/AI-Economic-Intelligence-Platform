package com.td.aieconomics.repository;

import com.td.aieconomics.entity.AiSummary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AiSummaryRepository extends JpaRepository<AiSummary, Long> {
}