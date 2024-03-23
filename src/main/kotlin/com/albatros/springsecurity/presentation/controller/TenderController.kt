package com.albatros.springsecurity.presentation.controller

import com.albatros.springsecurity.data.service.TenderService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/tender")
class TenderController(
    private val tenderService: TenderService
) {

    @GetMapping("/")
    fun getAll() = tenderService.getAllTenderProviders()

}