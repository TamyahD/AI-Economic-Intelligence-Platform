package com.td.aieconomics.service;

import com.td.aieconomics.ai.AiGenerationContext;
import com.td.aieconomics.entity.AiSummary;

public interface AiSummaryPersistenceService {
    AiSummary saveSummary(
            AiGenerationContext context
    );
}
