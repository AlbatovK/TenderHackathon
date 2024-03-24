package com.albatros.springsecurity.presentation.route

import com.albatros.springsecurity.data.model.database.TenderProvider
import com.albatros.springsecurity.data.repository.TenderProviderRepository
import com.vaadin.flow.component.Text
import com.vaadin.flow.component.button.Button
import com.vaadin.flow.component.grid.Grid
import com.vaadin.flow.component.notification.Notification
import com.vaadin.flow.component.orderedlayout.VerticalLayout
import com.vaadin.flow.router.Route
import com.vaadin.flow.spring.data.VaadinSpringDataHelpers

@Route("")
class MainRoute(
    repository: TenderProviderRepository
) : VerticalLayout() {

    init {
        add(
            Text("Lig ma who?"),
            Button(
                "Lig ma Balls"
            ) {
                Notification.show("CDs nuts").open()
            },
            Grid<TenderProvider>().apply {
                addColumn(TenderProvider::tenderId).setHeader("Tender Id")
                addColumn(TenderProvider::etpName).setHeader("Name")

                setItems {
                    repository.findAll(
                        VaadinSpringDataHelpers.toSpringPageRequest(it)
                    ).stream()
                }
            }
        )
    }
}
