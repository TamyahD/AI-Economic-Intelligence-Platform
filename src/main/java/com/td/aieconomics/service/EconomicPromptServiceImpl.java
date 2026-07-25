package com.td.aieconomics.service;

import com.td.aieconomics.entity.EconomicIndicator;
import org.springframework.stereotype.Service;

@Service
public class EconomicPromptServiceImpl implements EconomicPromptService {

    @Override
    public String buildEconomicSummaryPrompt(EconomicIndicator indicator) {
        StringBuilder prompt = new StringBuilder();
        prompt.append("""
                You are a senior macroeconomist.
                                
                Analyze the following economic indicator.
                                
                Provide:
                                
                1. A concise explanation.
                2. Why the indicator matters.
                3. Possible implications.
                4. Keep the response under 250 words.
                                
                Economic Indicator:
                                
                """);

        prompt.append("Name: ").append(indicator.getName()).append("\n");
        prompt.append("Category: ").append(indicator.getCategory()).append("\n");
        prompt.append("Current Value: ").append(indicator.getValue()).append("\n");
        prompt.append("Date: ").append(indicator.getDateCollected());

        return prompt.toString();
    }
}