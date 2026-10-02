package com.lameefu.view;

import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.HighlightCondition;
import com.vaadin.flow.router.HighlightConditions;
import com.vaadin.flow.router.RouterLink;

public class MainLayout extends AppLayout {
    public MainLayout() {

        H1 title = new H1("Financial Freedom");

        RouterLink homeLink = new RouterLink("Startseite", MainView.class);
        RouterLink financeLink = new RouterLink("Finanzen", FinanceView.class);
        HorizontalLayout navigation = new HorizontalLayout(homeLink, financeLink);
        navigation.setSpacing(true);
        HorizontalLayout navbar = new HorizontalLayout(title, navigation);

        navbar.setWidthFull();
        navbar.setPadding(true);
        navbar.setAlignItems(FlexComponent.Alignment.CENTER);
        navbar.setJustifyContentMode(FlexComponent.JustifyContentMode.BETWEEN);

        homeLink.setHighlightCondition(HighlightConditions.sameLocation());
        financeLink.setHighlightCondition(HighlightConditions.sameLocation());
        addToNavbar(navbar);
    }
}
