package com.albatros.springsecurity.config.webclient

import io.netty.channel.ChannelOption
import io.netty.handler.timeout.ReadTimeoutHandler
import io.netty.handler.timeout.WriteTimeoutHandler
import java.util.concurrent.TimeUnit
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.client.reactive.ReactorClientHttpConnector
import org.springframework.http.codec.xml.Jaxb2XmlDecoder
import org.springframework.http.codec.xml.Jaxb2XmlEncoder
import org.springframework.web.reactive.function.client.ExchangeStrategies
import org.springframework.web.reactive.function.client.WebClient
import reactor.netty.http.client.HttpClient

@Configuration
class WebClientConfig(
    private val tenderApiConfig: TenderApiConfig
) {

    fun clientHttpConnector() = ReactorClientHttpConnector(
        HttpClient.create().apply {
            option(ChannelOption.CONNECT_TIMEOUT_MILLIS, tenderApiConfig.timeoutMs).doOnConnected {
                it.addHandlerLast(ReadTimeoutHandler(tenderApiConfig.timeoutMs.toLong(), TimeUnit.MILLISECONDS))
                it.addHandlerLast(WriteTimeoutHandler(tenderApiConfig.timeoutMs.toLong(), TimeUnit.MILLISECONDS))
            }
        }
    )

    @Bean
    fun webClientWithTimeOut() = WebClient.builder().exchangeStrategies(
        ExchangeStrategies.builder().codecs {
            it.defaultCodecs().jaxb2Decoder(Jaxb2XmlDecoder())
            it.defaultCodecs().jaxb2Encoder(Jaxb2XmlEncoder())
        }.build()
    ).clientConnector(clientHttpConnector()).baseUrl(tenderApiConfig.apiUrl).build()
}