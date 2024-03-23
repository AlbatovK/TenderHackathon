package com.albatros.springsecurity.data.service

import com.albatros.springsecurity.config.webclient.TenderApiConfig
import com.albatros.springsecurity.data.model.dto.TenderDto
import com.albatros.springsecurity.data.model.dto.TenderInfoDto
import com.albatros.springsecurity.data.model.dto.TenderProviderDto
import org.springframework.http.MediaType
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import java.nio.charset.Charset

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

    fun getTendersByProviders(providerId: Int): MutableList<TenderDto>? = webClient
        .get()
        .uri("/export?e$providerId=$providerId&api_code=${tenderApiConfig.apiKey}&dtype=json")
        .accept(MediaType.APPLICATION_JSON)
        .retrieve()
        .bodyToFlux(TenderDto::class.java)
        .collectList()
        .block()

    fun getTenderInfoById(tenderId: Int): MutableList<TenderInfoDto>? = webClient
        .get()
        .uri("/export?e$tenderId=$tenderId&dtype=json")
        .accept(MediaType.APPLICATION_JSON)
        .acceptCharset(Charset.forName("UTF-8"))
        .retrieve()
        .bodyToFlux(TenderInfoDto::class.java)
        .collectList()
        .block()
}