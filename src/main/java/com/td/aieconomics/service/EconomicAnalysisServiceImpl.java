package com.td.aieconomics.service;

import com.td.aieconomics.ai.AiGenerationContext;
import com.td.aieconomics.entity.AiSummaryStatus;
import com.td.aieconomics.entity.EconomicIndicator;
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
        String response = chatClient.prompt().user(prompt).call().content();
        Instant end = Instant.now();
        long generationDuration = Duration.between(start, end).toMillis();

        AiGenerationContext context = new AiGenerationContext(
                indicator,
                prompt,
                response,
                "mistral",
                generationDuration,
                AiSummaryStatus.SUCCESS
        );
        persistenceService.saveSummary(context);

        return response;
    }
}