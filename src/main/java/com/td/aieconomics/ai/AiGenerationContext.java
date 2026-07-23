package com.td.aieconomics.ai;

import com.td.aieconomics.entity.AiSummaryStatus;
import com.td.aieconomics.entity.EconomicIndicator;

public record AiGenerationContext(
        EconomicIndicator economicIndicator,
        String prompt,
        String generatedResponse,
        String modelName,
        long generationDurationMs,
        AiSummaryStatus status
)
{
}