package com.td.aieconomics.service;

import com.td.aieconomics.entity.EconomicIndicator;

public interface EconomicPromptService {
    String buildEconomicSummaryPrompt(EconomicIndicator indicator);
}
