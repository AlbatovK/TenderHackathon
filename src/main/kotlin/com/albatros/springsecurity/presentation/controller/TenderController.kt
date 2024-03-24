package com.albatros.springsecurity.presentation.controller

import com.albatros.springsecurity.data.model.database.Tender
import com.albatros.springsecurity.data.model.dto.FullTextSearchRequest
import com.albatros.springsecurity.data.repository.TenderSearchRepository
import com.albatros.springsecurity.data.service.TenderApiService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class TenderController(
    private val apiService: TenderApiService,
    private val tenderRepository: TenderSearchRepository,
) {

    @PostMapping("/full-text-search")
    fun getAll(@RequestBody fullTextSearchRequest: FullTextSearchRequest): List<Tender> {
        return tenderRepository.fullTextSearchOr(
            fullTextSearchRequest.include, fullTextSearchRequest.exclude
        )
    }

    @GetMapping("/provider/")
    fun getAll() = apiService.getAllTenderProviders()

    @GetMapping("/provider/{id}/tender")
    fun getTendersByProviderId(@PathVariable id: Int) = apiService.getTendersByProviders(id)

    @GetMapping("/tender/customer")
    fun findAllByCustomerContainsIgnoreCase(@RequestParam customer: String, pageable: Pageable): Page<Tender> =
        tenderRepository.findAllByCustomerContainsIgnoreCase(customer, pageable)

    @GetMapping("/tender/region")
    fun findAllByRegionContainsIgnoreCase(@RequestParam region: String, pageable: Pageable): Page<Tender> =
        tenderRepository.findAllByRegionContainsIgnoreCase(region, pageable)

    @GetMapping("/tender/category")
    fun findAllByCategory(@RequestParam category: String, pageable: Pageable): Page<Tender> =
        tenderRepository.findAllByCategory(category, pageable)

    @GetMapping("/tender/etp")
    fun findAllByEtpEqualsIgnoreCase(@RequestParam etp: String, pageable: Pageable): Page<Tender> =
        tenderRepository.findAllByEtpEqualsIgnoreCase(etp, pageable)

    @GetMapping("/tender/name")
    fun findAllByTenderNameContainingIgnoreCase(
        @RequestParam(value = "query") query: String,
        pageable: Pageable
    ): Page<Tender> =
        tenderRepository.findAllByTenderNameContainingIgnoreCase(query, pageable)
}
