package com.td.aieconomics.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.PrePersist;
import jakarta.persistence.FetchType;
import jakarta.persistence.GenerationType;
import java.time.LocalDateTime;

@Entity
@Table(name = "ai_summary")
public class AiSummary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    @Column(nullable = false)
    private String summary;

    @Lob
    @Column(nullable = false)
    private String prompt;

    @Column(nullable = false)
    private String modelName;

    @Column(nullable = false)
    private Long generationDurationMs;

    @Column(nullable = false)
    private LocalDateTime generatedAt;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AiSummaryStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "economic_indicator_id",
            nullable = false
    )
    private EconomicIndicator economicIndicator;

    public AiSummary() {
    }

    @PrePersist
    public void prePersist() {
        generatedAt = LocalDateTime.now();
        if (status == null) {
            status = AiSummaryStatus.PENDING;
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getPrompt() {
        return prompt;
    }

    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public Long getGenerationDurationMs() {
        return generationDurationMs;
    }

    public void setGenerationDurationMs(Long generationDurationMs) {
        this.generationDurationMs = generationDurationMs;
    }

    public LocalDateTime getGeneratedAt() {
        return generatedAt;
    }

    public void setGeneratedAt(LocalDateTime generatedAt) {
        this.generatedAt = generatedAt;
    }

    public AiSummaryStatus getStatus() {
        return status;
    }

    public void setStatus(AiSummaryStatus status) {
        this.status = status;
    }

    public EconomicIndicator getEconomicIndicator() {
        return economicIndicator;
    }

    public void setEconomicIndicator(EconomicIndicator economicIndicator) {
        this.economicIndicator = economicIndicator;
    }
}
