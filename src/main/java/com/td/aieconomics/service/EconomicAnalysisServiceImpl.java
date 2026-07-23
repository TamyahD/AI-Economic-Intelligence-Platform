package com.td.aieconomics.service;

import com.td.aieconomics.entity.AiSummary;
import com.td.aieconomics.entity.AiSummaryStatus;
import com.td.aieconomics.entity.EconomicIndicator;
import com.td.aieconomics.repository.AiSummaryRepository;
import jakarta.transaction.Transactional;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;

@Service
@Transactional
public class EconomicAnalysisServiceImpl implements EconomicAnalysisService {
    private final ChatClient chatClient;
    private final EconomicPromptService promptService;
    private final AiSummaryPersistenceService persistenceService;

    public EconomicAnalysisServiceImpl(ChatClient chatClient, EconomicPromptService promptService, AiSummaryPersistenceService persistenceService) {
        this.chatClient = chatClient;
        this.promptService = promptService;
        this.persistenceService = persistenceService;
    }

    @Override
    public String generateSummary(EconomicIndicator indicator) {
        String prompt = promptService.buildEconomicSummaryPrompt(indicator);
        Instant start = Instant.now();
        String response = chatClient.prompt(prompt).call().content();
        Instant end = Instant.now();
        long generationDurationMs = Duration.between(start, end).toMillis();
        AiSummary savedSummary = persistenceService.saveSummary(
                indicator,
                prompt,
                response,
                "mistral",
                generationDurationMs,
                AiSummaryStatus.COMPLETED);
        if (response == null || response.isBlank()) {
            throw new IllegalStateException(
                    "AI returned an empty response.");
        }

        return response;
    }
}