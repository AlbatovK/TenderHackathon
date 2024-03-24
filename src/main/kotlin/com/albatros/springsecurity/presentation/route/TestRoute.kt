package com.albatros.springsecurity.presentation.route

import com.albatros.springsecurity.data.model.database.Tender
import com.albatros.springsecurity.data.model.database.TenderProvider
import com.albatros.springsecurity.data.repository.TenderProviderRepository
import com.albatros.springsecurity.data.repository.TenderSearchRepository
import com.vaadin.flow.component.Text
import com.vaadin.flow.component.button.Button
import com.vaadin.flow.component.button.ButtonVariant
import com.vaadin.flow.component.grid.Grid
import com.vaadin.flow.component.html.Paragraph
import com.vaadin.flow.component.icon.VaadinIcon
import com.vaadin.flow.component.notification.Notification
import com.vaadin.flow.component.orderedlayout.HorizontalLayout
import com.vaadin.flow.component.orderedlayout.VerticalLayout
import com.vaadin.flow.component.textfield.TextField
import com.vaadin.flow.router.Route
import com.vaadin.flow.spring.data.VaadinSpringDataHelpers


@Route("test")
class TestRoute(repository: TenderSearchRepository) : VerticalLayout() {

    init {
        val includeTextField = TextField()
        val excludeTextField = TextField()
        includeTextField.placeholder = "ключевые слова"
        excludeTextField.placeholder = "нежелательные слова"

        val sheet: Grid<Tender> = Grid<Tender>().apply {
            addColumn(Tender::apiTenderInfo).setHeader("apiTenderInfo")
            addColumn(Tender::category).setHeader("category")
            addColumn(Tender::customer).setHeader("customer")
            addColumn(Tender::date).setHeader("date")
            addColumn(Tender::endTime).setHeader("endTime")
            addColumn(Tender::etp).setHeader("etp")
            addColumn(Tender::fz).setHeader("fz")
            addColumn(Tender::tenderId).setHeader("tenderId")
            addColumn(Tender::price).setHeader("price")
            addColumn(Tender::region).setHeader("region")
            addColumn(Tender::tenderLink).setHeader("tenderLink")
            addColumn(Tender::tenderInnerLink).setHeader("tenderInnerLink")
            addColumn(Tender::tenderName).setHeader("tenderName")
            addColumn(Tender::tenderNumOuter).setHeader("tenderNumOuter")
            addColumn(Tender::userId).setHeader("userId")
            addColumn(Tender::metaData).setHeader("metaData")
            height = "600px"
        }

        val button = Button("Найти", VaadinIcon.SEARCH.create()){
            val includeText = includeTextField.value
            val excludeText = excludeTextField.value

            sheet.setItems(
                repository.fullTextSearchOr(
                    includeText,
                    excludeText,
                )
            )
        }

        button.addThemeVariants(
            ButtonVariant.LUMO_PRIMARY,
            ButtonVariant.LUMO_CONTRAST,
        )

        add(
            HorizontalLayout(
                includeTextField,
                excludeTextField,
                button
            ),
            sheet,
        )
    }
}
