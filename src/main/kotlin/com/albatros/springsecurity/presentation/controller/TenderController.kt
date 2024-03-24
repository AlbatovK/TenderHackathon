package com.albatros.springsecurity.presentation.controller

import com.albatros.springsecurity.data.model.database.Tender
import com.albatros.springsecurity.data.repository.TenderRepository
import com.albatros.springsecurity.data.service.TenderApiService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class TenderController(
    private val apiService: TenderApiService,
    private val tenderRepository: TenderRepository
) {

    @GetMapping("/provider/")
    fun getAll() = apiService.getAllTenderProviders()

    @GetMapping("/provider/{id}/tender")
    fun getTendersByProviderId(@PathVariable id: Int) = apiService.getTendersByProviders(id)

    @GetMapping("/tender/customer")
    fun findAllByCustomerContainsIgnoreCase(@RequestParam customer: String): List<Tender> =
        tenderRepository.findAllByCustomerContainsIgnoreCase(customer)

    @GetMapping("/tender/region")
    fun findAllByRegionContainsIgnoreCase(@RequestParam region: String): List<Tender> =
        tenderRepository.findAllByRegionContainsIgnoreCase(region)

    @GetMapping("/tender/category")
    fun findAllByCategory(@RequestParam category: String): List<Tender> = tenderRepository.findAllByCategory(category)

    @GetMapping("/tender/etp")
    fun findAllByEtpEqualsIgnoreCase(@RequestParam etp: String): List<Tender> =
        tenderRepository.findAllByEtpEqualsIgnoreCase(etp)

    @GetMapping("/tender/name")
    fun findAllByTenderNameContainingIgnoreCase(@RequestParam(value = "query") query: String): List<Tender> =
        tenderRepository.findAllByTenderNameContainingIgnoreCase(query)
}
