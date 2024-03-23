package com.albatros.springsecurity.data.service

import com.albatros.springsecurity.config.webclient.TenderApiConfig
import com.albatros.springsecurity.data.model.dto.TenderProviderDto
import org.springframework.http.MediaType
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient

@Service
class TenderService(
    private val tenderApiConfig: TenderApiConfig,
    private val webClient: WebClient
) {
    fun getAllTenderProviders(mode: String = "eauc"): MutableList<TenderProviderDto>? = webClient
        .get()
        .uri("/export?mode=$mode&api_code=${tenderApiConfig.apiKey}&dtype=json")
        .accept(MediaType.APPLICATION_JSON)
        .retrieve()
        .bodyToFlux(TenderProviderDto::class.java)
        .collectList()
        .block()



}