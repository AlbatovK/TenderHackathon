package com.albatros.springsecurity.data.service.impl

import com.albatros.springsecurity.config.webclient.TenderApiConfig
import com.albatros.springsecurity.data.model.dto.TenderDto
import com.albatros.springsecurity.data.model.dto.TenderInfoDto
import com.albatros.springsecurity.data.model.dto.TenderProviderDto
import com.albatros.springsecurity.data.service.TenderApiService
import org.springframework.cache.annotation.Cacheable
import org.springframework.http.MediaType
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Mono

@Service
class TenderApiServiceImpl(
    private val tenderApiConfig: TenderApiConfig,
    private val webClient: WebClient
) : TenderApiService {

    override fun getAllTenderProviders(mode: String): MutableList<TenderProviderDto>? = webClient
        .get()
        .uri("/export?mode=$mode&api_code=${tenderApiConfig.apiKey}&dtype=json")
        .accept(MediaType.APPLICATION_JSON)
        .retrieve()
        .bodyToFlux(TenderProviderDto::class.java)
        .collectList()
        .block()

    override fun getTendersByProviders(providerId: Int): List<TenderDto>? = webClient
        .get()
        .uri("/export?e$providerId=$providerId&api_code=${tenderApiConfig.apiKey}&dtype=json")
        .accept(MediaType.APPLICATION_JSON)
        .retrieve()
        .bodyToFlux(TenderDto::class.java)
        .collectList()
        .onErrorResume {
            Mono.just(
                emptyList<TenderDto>()
            )
        }
        .block()
        ?.drop(1)

    @Cacheable(value = ["TenderInfo"], key = "#tenderId")
    override fun getTenderInfoById(tenderId: Int): MutableList<TenderInfoDto>? = webClient
        .get()
        .uri("/export?id=$tenderId&dtype=json")
        .accept(MediaType.APPLICATION_JSON)
        .retrieve()
        .bodyToFlux(TenderInfoDto::class.java)
        .collectList()
        .block()
}
