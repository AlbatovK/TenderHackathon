package com.albatros.springsecurity.presentation.controller

import com.albatros.springsecurity.data.service.TenderApiService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class TenderController(
    private val tenderService: TenderApiService
) {

    @GetMapping("providers/")
    fun getAll() = tenderService.getAllTenderProviders()

    @GetMapping("tenders/{id}")
    fun getTendersByProviderId(@PathVariable id: Int) = tenderService.getTendersByProviders(id)

    @GetMapping("tenders/{id}/info")
    fun getTenderInfoById(@PathVariable id: Int) = tenderService.getTenderInfoById(id)
}
