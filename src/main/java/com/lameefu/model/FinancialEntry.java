package com.lameefu.model;

import jakarta.persistence.*;

@Entity
@Table(name = "financial_entries")
public class FinancialEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String type;

    @Column(nullable = false)
    private double amount;

    public FinancialEntry(String name, String type, double amount) {
        this.name = name;
        this.type = type;
        this.amount = amount;
    }

    protected FinancialEntry() {

    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public double getAmount() {
        return amount;
    }


}
