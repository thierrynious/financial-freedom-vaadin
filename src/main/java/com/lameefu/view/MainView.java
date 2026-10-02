package com.lameefu.view;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

@Route(value = "", layout = MainLayout.class)
public class MainView extends VerticalLayout {

    public MainView() {

        H1 title =
                new H1("Financial Freedom");

        Paragraph description =
                new Paragraph("Dein Weg zur finanziellen Freiheit");

        Span info =
                new Span("Baue Vermögen auf und erhöhe dein passives Einkommen.");

        TextField nameField =
                new TextField("Spielername");

        nameField.setRequiredIndicatorVisible(true);

        nameField.setErrorMessage(
                "Bitte gib einen Spielernamen ein."
        );

        Select<String> languageSelect =
                new Select<>();

        languageSelect.setLabel("Sprache");

        languageSelect.setItems(
                "Deutsch",
                "English",
                "Français"
        );

        languageSelect.setValue("Deutsch");

        Button startButton =
                new Button("Neues Spiel starten");

        startButton.addThemeVariants(
                ButtonVariant.LUMO_PRIMARY
        );

        nameField.addValueChangeListener(event -> {
            if (!event.getValue().isBlank()) {
                nameField.setInvalid(false);
            }
        });

        startButton.addClickListener(event -> {

            String name =
                    nameField.getValue();

            if (name.isBlank()) {
                nameField.setInvalid(true);
                return;
            }

            nameField.setInvalid(false);

            String language =
                    languageSelect.getValue();

            info.setText(
                    "Willkommen " + name +
                            "! Ausgewählte Sprache: " + language
            );

            Notification.show(
                    "Willkommen " + name +
                            "! Sprache: " + language
            );

            UI.getCurrent().navigate(
                    GameView.class,
                    name
            );
        });

        HorizontalLayout inputLayout =
                new HorizontalLayout(
                        nameField,
                        languageSelect
                );

        inputLayout.setSpacing(true);
        inputLayout.setAlignItems(Alignment.END);

        VerticalLayout formLayout =
                new VerticalLayout(
                        inputLayout,
                        startButton
                );

        formLayout.setSpacing(true);
        formLayout.setPadding(false);
        formLayout.setAlignItems(Alignment.CENTER);
        formLayout.setMaxWidth("600px");
        formLayout.setWidthFull();

        setSpacing(true);
        setPadding(true);
        setAlignItems(Alignment.CENTER);

        add(
                title,
                description,
                info,
                formLayout
        );
    }
}