package com.albatros.springsecurity.config.webclient

import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotEmpty
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.validation.annotation.Validated

@Validated
@EnableConfigurationProperties(TenderApiConfig::class)
@ConfigurationProperties(prefix = "tender-config")
data class TenderApiConfig(
    @field:NotEmpty
    val apiUrl: String,
    @field:NotEmpty
    val apiKey: String,
    @field:Min(0)
    val timeoutMs: Int
)
