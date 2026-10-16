package com.lameefu.view;

import com.lameefu.model.FinancialEntry;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

@Route(
        value = "finances",
        layout = MainLayout.class
)
public class FinanceView extends VerticalLayout {

    public FinanceView() {

        H1 title =
                new H1("Finanzübersicht");

        Paragraph description =
                new Paragraph(
                        "Übersicht deiner monatlichen Einnahmen und Ausgaben."
                );

        NumberFormat currencyFormat =
                NumberFormat.getCurrencyInstance(
                        Locale.GERMANY
                );

        FinancialEntry salary =
                new FinancialEntry(
                        "Gehalt",
                        "Einnahme",
                        3000
                );

        FinancialEntry rent =
                new FinancialEntry(
                        "Miete",
                        "Ausgabe",
                        900
                );

        FinancialEntry dividends =
                new FinancialEntry(
                        "Dividenden",
                        "Einnahme",
                        150
                );

        FinancialEntry groceries =
                new FinancialEntry(
                        "Lebensmittel",
                        "Ausgabe",
                        300
                );

        List<FinancialEntry> entries =
                List.of(
                        salary,
                        rent,
                        dividends,
                        groceries
                );

        // Gesamteinnahmen berechnen
        double totalIncome =
                entries.stream()
                        .filter(entry ->
                                entry.getType()
                                        .equals("Einnahme")
                        )
                        .mapToDouble(
                                FinancialEntry::getAmount
                        )
                        .sum();

        // Gesamtausgaben berechnen
        double totalExpenses =
                entries.stream()
                        .filter(entry ->
                                entry.getType()
                                        .equals("Ausgabe")
                        )
                        .mapToDouble(
                                FinancialEntry::getAmount
                        )
                        .sum();

        // Saldo berechnen
        double balance =
                totalIncome - totalExpenses;

        // Einnahmen anzeigen
        H3 incomeTitle =
                new H3("Einnahmen");

        Span incomeValue =
                new Span(
                        currencyFormat.format(totalIncome)
                );

        VerticalLayout incomeLayout =
                new VerticalLayout(
                        incomeTitle,
                        incomeValue
                );

        // Ausgaben anzeigen
        H3 expensesTitle =
                new H3("Ausgaben");

        Span expensesValue =
                new Span(
                        currencyFormat.format(totalExpenses)
                );

        VerticalLayout expensesLayout =
                new VerticalLayout(
                        expensesTitle,
                        expensesValue
                );

        // Saldo anzeigen
        H3 balanceTitle =
                new H3("Saldo");

        Span balanceValue =
                new Span(
                        currencyFormat.format(balance)
                );

        VerticalLayout balanceLayout =
                new VerticalLayout(
                        balanceTitle,
                        balanceValue
                );

        // Kennzahlen anordnen
        HorizontalLayout summaryLayout =
                new HorizontalLayout(
                        incomeLayout,
                        expensesLayout,
                        balanceLayout
                );

        summaryLayout.setWidthFull();

        summaryLayout.setFlexGrow(
                1,
                incomeLayout,
                expensesLayout,
                balanceLayout
        );

        // Grid erstellen
        Grid<FinancialEntry> financeGrid =
                new Grid<>();

        financeGrid.addColumn(
                        FinancialEntry::getName
                )
                .setHeader("Name")
                .setAutoWidth(true)
                .setFlexGrow(1);

        financeGrid.addColumn(
                        FinancialEntry::getType
                )
                .setHeader("Typ")
                .setAutoWidth(true)
                .setFlexGrow(0);

        financeGrid.addColumn(
                        entry -> currencyFormat.format(
                                entry.getAmount()
                        )
                )
                .setHeader("Betrag")
                .setAutoWidth(true)
                .setFlexGrow(0);

        financeGrid.setItems(entries);
        financeGrid.setWidthFull();

        // Suchfeld erstellen
        TextField filterField =
                new TextField("Suche");

        filterField.setPlaceholder(
                "Finanzposition suchen..."
        );

        filterField.setValueChangeMode(
                ValueChangeMode.EAGER
        );

        filterField.addValueChangeListener(
                event -> {

                    String filter =
                            event.getValue();

                    List<FinancialEntry> filteredEntries =
                            entries.stream()
                                    .filter(entry ->
                                            entry.getName()
                                                    .toLowerCase()
                                                    .contains(
                                                            filter.toLowerCase()
                                                    )
                                    )
                                    .toList();

                    financeGrid.setItems(
                            filteredEntries
                    );
                }
        );

        // Detaildialog erstellen
        Dialog detailsDialog =
                new Dialog();

        detailsDialog.setHeaderTitle(
                "Finanzdetails"
        );

        Paragraph nameText =
                new Paragraph();

        Paragraph typeText =
                new Paragraph();

        Paragraph amountText =
                new Paragraph();

        detailsDialog.add(
                nameText,
                typeText,
                amountText
        );

        Button closeButton =
                new Button(
                        "Schließen",
                        event -> {

                            detailsDialog.close();

                            Notification.show(
                                    "Detailansicht geschlossen"
                            );
                        }
                );

        detailsDialog.getFooter().add(
                closeButton
        );

        financeGrid.addItemClickListener(
                event -> {

                    FinancialEntry selectedEntry =
                            event.getItem();

                    nameText.setText(
                            "Name: " + selectedEntry.getName()
                    );

                    typeText.setText(
                            "Typ: " + selectedEntry.getType()
                    );

                    amountText.setText(
                            "Betrag: " + currencyFormat.format(
                                    selectedEntry.getAmount()
                            )
                    );

                    detailsDialog.open();
                }
        );

        // Komponenten anzeigen
        add(
                title,
                description,
                summaryLayout,
                filterField,
                financeGrid
        );
    }
}