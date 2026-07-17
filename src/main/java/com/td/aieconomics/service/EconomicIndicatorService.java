package com.td.aieconomics.service;

import com.td.aieconomics.entity.EconomicIndicator;
import com.td.aieconomics.repository.EconomicIndicatorRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class EconomicIndicatorService {
    private final EconomicIndicatorRepository repository;

    public EconomicIndicatorService(EconomicIndicatorRepository repository) {
        this.repository = repository;
    }

    public List<EconomicIndicator> getAllIndicators() {
        return repository.findAll();
    }

    public Optional<EconomicIndicator> getIndicatorById(Long id) {
        return repository.findById(id);
    }

    public EconomicIndicator createIndicator(EconomicIndicator indicator) {
        return repository.save(indicator);
    }

    public EconomicIndicator updateIndicator(Long id, EconomicIndicator updatedIndicator) {
        return repository.findById(id)
                .map(existing -> {
                    existing.setName(updatedIndicator.getName());
                    existing.setCountry(updatedIndicator.getCountry());
                    existing.setCategory(updatedIndicator.getCategory());
                    existing.setValue(updatedIndicator.getValue());
                    existing.setUnit(updatedIndicator.getUnit());
                    existing.setSource(updatedIndicator.getSource());
                    existing.setDateCollected(updatedIndicator.getDateCollected());

                    return repository.save(existing);
                })
                .orElseThrow(() -> new RuntimeException("Indicator not found."));
    }

    public void deleteIndicator(Long id) {

        repository.deleteById(id);

    }
}
