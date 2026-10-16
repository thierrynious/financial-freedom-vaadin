package com.lameefu.model;

public class FinancialEntry {

    private String name;
    private String type;
    private double amount;

    public FinancialEntry(String name, String type, double amount) {
        this.name = name;
        this.type = type;
        this.amount = amount;
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
