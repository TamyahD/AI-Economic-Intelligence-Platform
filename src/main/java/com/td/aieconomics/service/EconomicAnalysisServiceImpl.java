package com.td.aieconomics.service;

import com.td.aieconomics.entity.EconomicIndicator;
import com.td.aieconomics.repository.AiSummaryRepository;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
public class EconomicAnalysisServiceImpl implements EconomicAnalysisService {
    private final ChatClient chatClient;
    private final EconomicPromptService promptService;
    private final AiSummaryRepository aiSummaryRepository;

    public EconomicAnalysisServiceImpl(
            ChatClient chatClient,
            EconomicPromptService promptService,
            AiSummaryRepository aiSummaryRepository) {

        this.chatClient = chatClient;
        this.promptService = promptService;
        this.aiSummaryRepository = aiSummaryRepository;
    }

    @Override
    public String generateSummary(EconomicIndicator indicator) {
        String prompt = promptService.buildEconomicSummaryPrompt(indicator);

        String response = chatClient.prompt(prompt).call().content();
        return response;
    }
}