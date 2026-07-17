package com.td.aieconomics.controller;

import com.td.aieconomics.entity.EconomicIndicator;
import com.td.aieconomics.service.EconomicIndicatorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/indicators")
public class EconomicIndicatorController {
    private final EconomicIndicatorService service;

    public EconomicIndicatorController(EconomicIndicatorService service) {
        this.service = service;
    }

    @GetMapping
    public List<EconomicIndicator> getAllIndicators() {
        return service.getAllIndicators();
    }

    @GetMapping("/{id}")
    public ResponseEntity<EconomicIndicator> getIndicator(@PathVariable Long id) {
        return service.getIndicatorById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<EconomicIndicator> createIndicator(@RequestBody EconomicIndicator indicator) {
        EconomicIndicator created = service.createIndicator(indicator);

        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EconomicIndicator> updateIndicator(@PathVariable Long id, @RequestBody EconomicIndicator indicator) {
        EconomicIndicator updated = service.updateIndicator(id, indicator);

        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteIndicator(@PathVariable Long id) {
        service.deleteIndicator(id);

        return ResponseEntity.noContent().build();
    }
}
