package com.albatros.springsecurity.presentation.route

import com.albatros.springsecurity.data.model.database.Tender
import com.albatros.springsecurity.data.repository.TenderSearchRepository
import com.vaadin.flow.component.ClickEvent
import com.vaadin.flow.component.ComponentEventListener
import com.vaadin.flow.component.button.Button
import com.vaadin.flow.component.button.ButtonVariant
import com.vaadin.flow.component.contextmenu.MenuItem
import com.vaadin.flow.component.grid.Grid
import com.vaadin.flow.component.html.Div
import com.vaadin.flow.component.icon.VaadinIcon
import com.vaadin.flow.component.menubar.MenuBar
import com.vaadin.flow.component.menubar.MenuBarVariant
import com.vaadin.flow.component.orderedlayout.HorizontalLayout
import com.vaadin.flow.component.orderedlayout.VerticalLayout
import com.vaadin.flow.component.tabs.TabSheet
import com.vaadin.flow.component.textfield.TextField
import com.vaadin.flow.router.Route
import com.vaadin.flow.spring.data.VaadinSpringDataHelpers
import org.springframework.data.domain.Pageable

@Route("test")
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

//        val dialog = Dialog().apply {
//            headerTitle = "Test"
//        }

        val button = Button("Найти", VaadinIcon.SEARCH.create()) {
            val includeText = includeTextField.value
            val excludeText = excludeTextField.value

//            dialog.open()

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
//            dialog
        )
    }

    // нейминг отдыхает
    private fun getLockedSearch(repository: TenderSearchRepository): Div {
        val menuBar = MenuBar()
        val div = Div(getBaseTable())
        val listener1: ComponentEventListener<ClickEvent<MenuItem>> =
            ComponentEventListener<ClickEvent<MenuItem>> {
                div.removeAll() // плохой код, потом переделать

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
                    menuBar,
                    categoryText,
                    button,
                )

                div.add(
                    hl,
                    sheet,
                )
            }

        val listener2: ComponentEventListener<ClickEvent<MenuItem>> =
            ComponentEventListener<ClickEvent<MenuItem>> {
                div.removeAll() // плохой код, потом переделать

                val sheet: Grid<Tender> = getBaseTable()

                val regionText = TextField()

                regionText.placeholder = "Регион"

                val button = Button("Найти", VaadinIcon.SEARCH.create()) {
                    val region = regionText.value

                    sheet.setItems {
                        repository.findAllByRegionIgnoreCase(
                            region,
                            VaadinSpringDataHelpers.toSpringPageRequest(it)
                        ).stream()
                    }
                }

                button.addThemeVariants(
                    ButtonVariant.LUMO_PRIMARY,
                    ButtonVariant.LUMO_CONTRAST,
                )

                val hl = HorizontalLayout(
                    menuBar,
                    regionText,
                    button,
                )

                div.add(
                    hl,
                    sheet,
                )
            }

        menuBar.addItem("Поиск по категории", listener1)
        menuBar.addItem("Поиск по регионам", listener2)

        menuBar.addThemeVariants(
            MenuBarVariant.LUMO_PRIMARY,
            MenuBarVariant.LUMO_CONTRAST,
        )

        val horizontalLayout = HorizontalLayout(
            menuBar,
        )

        return Div(
            horizontalLayout,
            div,
        )
    }

    private fun getBaseTable(): Grid<Tender> {
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

        return sheet
    }
}
