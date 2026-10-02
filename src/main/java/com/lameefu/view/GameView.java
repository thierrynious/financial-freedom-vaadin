package com.lameefu.view;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;

@Route(value = "game", layout = MainLayout.class)
public class GameView
        extends VerticalLayout
        implements HasUrlParameter<String> {

    private final Paragraph playerInfo;

    public GameView() {

        H1 title =
                new H1("Financial Freedom - Spiel");

        Paragraph description =
                new Paragraph(
                        "Hier wird später unser Spiel stattfinden."
                );

        playerInfo =
                new Paragraph("Spieler wird geladen...");

        RouterLink financeLink =
                new RouterLink(
                        "Zur Finanzübersicht",
                        FinanceView.class
                );

        Button backButton =
                new Button("Zurück zur Startseite");

        backButton.addClickListener(event -> {
            UI.getCurrent().navigate("");
        });

        add(
                title,
                description,
                playerInfo,
                financeLink,
                backButton
        );
    }

    @Override
    public void setParameter(
            BeforeEvent event,
            String parameter
    ) {

        playerInfo.setText(
                "Spieler: " + parameter
        );
    }
}