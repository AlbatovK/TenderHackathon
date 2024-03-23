package com.albatros.springsecurity.presentation.route

import com.albatros.springsecurity.data.model.dto.TenderProviderDto
import com.albatros.springsecurity.data.service.TenderService
import com.vaadin.flow.component.Text
import com.vaadin.flow.component.button.Button
import com.vaadin.flow.component.grid.Grid
import com.vaadin.flow.component.notification.Notification
import com.vaadin.flow.component.orderedlayout.VerticalLayout
import com.vaadin.flow.router.Route

@Route("")
class MainRoute(
    tenderService: TenderService
) : VerticalLayout() {

    init {
        add(
            Text("Lig ma who?"),
            Button(
                "Lig ma Balls"
            ) {
                Notification.show("CDs nuts").open()
            },
            Grid<TenderProviderDto>().apply {
                addColumn(TenderProviderDto::id).setHeader("Id")
                addColumn(TenderProviderDto::etpName).setHeader("Name")

                setItems(
                    tenderService.getAllTenderProviders()
                )
            }
        )
    }
}