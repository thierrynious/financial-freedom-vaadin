package com.lameefu;

import com.lameefu.model.FinancialEntry;
import com.lameefu.repository.FinancialEntryRepository;
import com.lameefu.service.FinancialEntryService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private final FinancialEntryService service;
    private final FinancialEntryRepository repository;

    public DataInitializer(FinancialEntryService service, FinancialEntryRepository repository) {
        this.service = service;
        this.repository = repository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (repository.count() == 0) {
            service.saveEntry(new FinancialEntry("Gehalt","Einnahme",3000));
            service.saveEntry(new FinancialEntry("Miete","Ausgabe",900));
            service.saveEntry(new FinancialEntry("Dividenden","Einnahme",150));
            service.saveEntry(new FinancialEntry("Lebensmittel","Ausgabe",300));
            service.saveEntry( new FinancialEntry("Nebenjob","Einnahme",250));
        }
    }
}
