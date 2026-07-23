package com.td.aieconomics.service;

import com.td.aieconomics.ai.AiGenerationContext;
import com.td.aieconomics.entity.AiSummary;
import com.td.aieconomics.repository.AiSummaryRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
public class AiSummaryPersistenceServiceImpl implements AiSummaryPersistenceService {
    private final AiSummaryRepository aiSummaryRepository;

    public AiSummaryPersistenceServiceImpl(AiSummaryRepository aiSummaryRepository) {
        this.aiSummaryRepository = aiSummaryRepository;
    }

    @Override
    public AiSummary saveSummary(AiGenerationContext context) {
        AiSummary aiSummary = new AiSummary();
        aiSummary.setEconomicIndicator(context.economicIndicator());
        aiSummary.setPrompt(context.prompt());
        aiSummary.setSummary(context.generatedResponse());
        aiSummary.setModelName(context.modelName());
        aiSummary.setGenerationDurationMs(context.generationDurationMs());
        aiSummary.setGeneratedAt(LocalDateTime.now());
        aiSummary.setStatus(context.status());

        return aiSummaryRepository.save(aiSummary);
    }
}