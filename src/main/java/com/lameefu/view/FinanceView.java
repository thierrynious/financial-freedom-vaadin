package com.lameefu.view;

import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route(value = "finances", layout = MainLayout.class)
public class FinanceView extends VerticalLayout {

    public FinanceView() {

        H1 title = new H1("Finanzübersicht");

        Paragraph description = new Paragraph("Hier wird später deine finanzielle Situation angezeigt.");

        add(title, description);
    }
}
