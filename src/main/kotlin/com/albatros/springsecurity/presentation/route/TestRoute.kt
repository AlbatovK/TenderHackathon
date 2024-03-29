package com.albatros.springsecurity.presentation.route

import com.albatros.springsecurity.data.model.database.Tender
import com.albatros.springsecurity.data.repository.TenderSearchRepository
import com.vaadin.flow.component.button.Button
import com.vaadin.flow.component.button.ButtonVariant
import com.vaadin.flow.component.grid.Grid
import com.vaadin.flow.component.grid.GridVariant
import com.vaadin.flow.component.html.Div
import com.vaadin.flow.component.icon.VaadinIcon
import com.vaadin.flow.component.orderedlayout.HorizontalLayout
import com.vaadin.flow.component.orderedlayout.VerticalLayout
import com.vaadin.flow.component.select.Select
import com.vaadin.flow.component.tabs.TabSheet
import com.vaadin.flow.component.textfield.TextField
import com.vaadin.flow.router.Route
import com.vaadin.flow.spring.data.VaadinSpringDataHelpers

@Route("")
class TestRoute(repository: TenderSearchRepository) : VerticalLayout() {

    init {

        val tabSheet = TabSheet().apply {
            width = "100%"
        }

        tabSheet.add(
            "Свободный поиск",
            getWildSearch(repository),
        )

        tabSheet.add(
            "Поиск по столбцам",
            Div(
                getLockedSearch(repository),
            )
        )

        add(
            tabSheet
        )
    }

    private fun getWildSearch(repository: TenderSearchRepository): Div {
        val includeTextField = TextField()
        val excludeTextField = TextField()
        includeTextField.placeholder = "Ключевые слова"
        excludeTextField.placeholder = "Нежелательные слова"

        val sheet: Grid<Tender> = getBaseTable()

        val button = Button("Найти", VaadinIcon.SEARCH.create()) {
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

        val horizontalLayout = HorizontalLayout(
            includeTextField,
            excludeTextField,
            button,
        )

        return Div(
            horizontalLayout,
            sheet,
        )
    }

    private fun getLockedSearch(repository: TenderSearchRepository): Div {

        val select: Select<String> = Select()
        select.label = "Тип поиска"
        select.setItems(
            "По категории",
            "По площадке",
            "По тендеру",
            "По покупателю",
        )

        val div = Div(getBaseTable())

        select.addValueChangeListener {
            if (select.value == "По категории") {
                div.removeAll()
                val sheet: Grid<Tender> = getBaseTable()

                val categoryText = TextField()
                categoryText.placeholder = "Категория"

                val button = Button("Найти", VaadinIcon.SEARCH.create()) {
                    val category = categoryText.value

                    sheet.setItems {
                        repository.findAllByCategoryIgnoreCase(
                            category,
                            VaadinSpringDataHelpers.toSpringPageRequest(it)
                        ).stream()
                    }
                }

                button.addThemeVariants(
                    ButtonVariant.LUMO_PRIMARY,
                    ButtonVariant.LUMO_CONTRAST,
                )

                val hl = HorizontalLayout(
                    categoryText,
                    button,
                )

                div.add(
                    hl,
                    sheet,
                )
            }
            if (select.value == "По площадке") {
                div.removeAll()

                val sheet: Grid<Tender> = getBaseTable()

                val etpText = TextField()

                etpText.placeholder = "Площадка"

                val button = Button("Найти", VaadinIcon.SEARCH.create()) {
                    val etp = etpText.value

                    sheet.setItems {
                        repository.findAllByEtpEqualsIgnoreCase(
                            etp,
                            VaadinSpringDataHelpers.toSpringPageRequest(it)
                        ).stream()
                    }
                }

                button.addThemeVariants(
                    ButtonVariant.LUMO_PRIMARY,
                    ButtonVariant.LUMO_CONTRAST,
                )

                val hl = HorizontalLayout(
                    etpText,
                    button,
                )

                div.add(
                    hl,
                    sheet,
                )
            }
            if (select.value == "По тендеру") {
                div.removeAll()

                val sheet: Grid<Tender> = getBaseTable()

                val tenderText = TextField()

                tenderText.placeholder = "Тендер"

                val button = Button("Найти", VaadinIcon.SEARCH.create()) {
                    val tender = tenderText.value

                    sheet.setItems {
                        repository.findAllByTenderNameContainingIgnoreCase(
                            tender,
                            VaadinSpringDataHelpers.toSpringPageRequest(it)
                        ).stream()
                    }
                }

                button.addThemeVariants(
                    ButtonVariant.LUMO_PRIMARY,
                    ButtonVariant.LUMO_CONTRAST,
                )

                val hl = HorizontalLayout(
                    tenderText,
                    button,
                )

                div.add(
                    hl,
                    sheet,
                )
            }
            if (select.value == "По покупателю") {
                div.removeAll()

                val sheet: Grid<Tender> = getBaseTable()

                val customerText = TextField()

                customerText.placeholder = "Покупатель"

                val button = Button("Найти", VaadinIcon.SEARCH.create()) {
                    val customer = customerText.value

                    sheet.setItems {
                        repository.findAllByCustomerContainsIgnoreCase(
                            customer,
                            VaadinSpringDataHelpers.toSpringPageRequest(it)
                        ).stream()
                    }
                }

                button.addThemeVariants(
                    ButtonVariant.LUMO_PRIMARY,
                    ButtonVariant.LUMO_CONTRAST,
                )

                val hl = HorizontalLayout(
                    customerText,
                    button,
                )

                div.add(
                    hl,
                    sheet,
                )
            }
        }

        val horizontalLayout = HorizontalLayout(
            select,
        )

        return Div(
            horizontalLayout,
            div,
        )
    }

    private fun getBaseTable(): Grid<Tender> {

        val sheet: Grid<Tender> = Grid<Tender>().apply {
            addColumn(Tender::tenderName)
                .setHeader("Тендер")
                .setSortable(true)
            addColumn(Tender::category)
                .setHeader("Категория")
                .setAutoWidth(true)
                .setFlexGrow(0)
                .setSortable(true)
            addColumn(Tender::etp)
                .setHeader("Площадка")
                .setSortable(true)
            addColumn(Tender::customer)
                .setHeader("Покупатель")
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

        sheet.addThemeVariants(
            GridVariant.LUMO_WRAP_CELL_CONTENT
        )

        return sheet
    }
}
