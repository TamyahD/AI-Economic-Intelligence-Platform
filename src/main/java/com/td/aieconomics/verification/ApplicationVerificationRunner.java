package com.td.aieconomics.verification;

import com.td.aieconomics.entity.AiSummary;
import com.td.aieconomics.entity.EconomicIndicator;
import com.td.aieconomics.repository.AiSummaryRepository;
import com.td.aieconomics.repository.EconomicIndicatorRepository;
import com.td.aieconomics.service.EconomicAnalysisService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

@Slf4j
@Component
@Profile("dev")
@RequiredArgsConstructor
public class ApplicationVerificationRunner implements CommandLineRunner {
    private final EconomicIndicatorRepository economicIndicatorRepository;
    private final EconomicAnalysisService economicAnalysisService;
    private final AiSummaryRepository aiSummaryRepository;

    @PostConstruct
    public void init() {
        log.info(">>> ApplicationVerificationRunner bean created");
    }

    @Override
    public void run(String... args) {
        log.info(">>> ENTERED ApplicationVerificationRunner");
        log.info("");
        log.info("=================================================");
        log.info(" AI ECONOMIC INTELLIGENCE VERIFICATION");
        log.info("=================================================");
        EconomicIndicator indicator;
        Optional<EconomicIndicator> optionalIndicator = economicIndicatorRepository.findAll().stream().findFirst();

        if (optionalIndicator.isPresent()) {
            indicator = optionalIndicator.get();
        }
        else {
            log.info("No EconomicIndicator found.");
            log.info("Creating verification indicator...");

            indicator = new EconomicIndicator();
            indicator.setName("Federal Funds Rate");
            indicator.setCountry("United States");
            indicator.setCategory("Monetary Policy");
            indicator.setValue(new BigDecimal("4.50"));
            indicator.setUnit("Percent");
            indicator.setSource("Verification Data");
            indicator.setDateCollected(LocalDate.now());
            indicator = economicIndicatorRepository.save(indicator);

            log.info("Verification indicator created.");
        }
//        Optional<EconomicIndicator> optionalIndicator = economicIndicatorRepository.findAll().stream().findFirst();
//        if (optionalIndicator.isEmpty()) {
//            log.warn("No EconomicIndicator records found.");
//            log.warn("Verification skipped.");
//
//            return;
//        }
//
//        EconomicIndicator indicator = optionalIndicator.get();

        String summary = economicAnalysisService.generateSummary(indicator);
        log.info("Economic Indicator Loaded ......... PASS");
        if (summary == null || summary.isBlank()) {
            throw new IllegalStateException("AI response was empty.");
        }
        log.info("Mistral Response Received ......... PASS");

        AiSummary latestSummary = aiSummaryRepository.findTopByOrderByGeneratedAtDesc()
                .orElseThrow(() -> new IllegalStateException("No AiSummary persisted."));

        log.info("AI Summary Persisted .............. PASS");

        if (!latestSummary.getEconomicIndicator().getId().equals(indicator.getId())) {
            throw new IllegalStateException("EconomicIndicator relationship invalid.");
        }

        log.info("Relationship Verified ............. PASS");
        log.info("Generation Time ................... {} ms", latestSummary.getGenerationDurationMs());
        log.info("Model ............................. {}", latestSummary.getModelName());
        log.info("Overall Status .................... SUCCESS");
        log.info("=================================================");
    }
}