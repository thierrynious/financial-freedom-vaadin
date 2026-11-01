package com.lameefu.service;

import com.lameefu.model.FinancialEntry;
import com.lameefu.repository.FinancialEntryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FinancialEntryService {

    private final FinancialEntryRepository repository;

    public FinancialEntryService(FinancialEntryRepository repository) {
        this.repository = repository;
    }

    public List<FinancialEntry> getAllEntries() {
        return repository.findAll();
    }

    public double calculateIncome() {
        return getAllEntries().stream()
                .filter(entry -> entry.getType().equals("Einnahme"))
                .mapToDouble(FinancialEntry::getAmount)
                .sum();
    }

    public double calculateExpenses() {
        return getAllEntries().stream()
                .filter(entry -> entry.getType().equals("Ausgabe"))
                .mapToDouble(FinancialEntry::getAmount)
                .sum();
    }

    public double calculateBalance() {
        return calculateIncome() - calculateExpenses();
    }

    public FinancialEntry saveEntry(FinancialEntry entry) {
        return repository.save(entry);
    }
}
