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
        includeTextField.placeholder = "Ключевые слова"
        excludeTextField.placeholder = "Нежелательные слова"

        val sheet: Grid<Tender> = Grid<Tender>().apply {
            addColumn(Tender::category)
                .setHeader("Категория")
                .setSortable(true)
            addColumn(Tender::region)
                .setHeader("Регион")
                .setSortable(true)
            addColumn(Tender::customer)
                .setHeader("Покупатель")
                .setSortable(true)
            addColumn(Tender::tenderName)
                .setHeader("Тендер")
                .setSortable(true)
            addColumn(Tender::date)
                .setHeader("Дата начала")
                .setAutoWidth(true)
                .setFlexGrow(0)
                .setSortable(true)
            addColumn(Tender::endTime)
                .setHeader("Дата конца")
                .setAutoWidth(true)
                .setFlexGrow(0)
                .setSortable(true)
            addColumn(Tender::price)
                .setHeader("Цена")
                .setAutoWidth(true)
                .setFlexGrow(0)
                .setSortable(true)

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
