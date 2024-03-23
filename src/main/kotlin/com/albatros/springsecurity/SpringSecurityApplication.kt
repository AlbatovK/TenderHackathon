package com.albatros.springsecurity

import com.albatros.springsecurity.config.api.ApiInfoConfig
import com.albatros.springsecurity.config.webclient.TenderApiConfig
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication
import org.springframework.cache.annotation.EnableCaching
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories

@EnableCaching
@SpringBootApplication
@EnableConfigurationProperties(ApiInfoConfig::class, TenderApiConfig::class)
@EnableElasticsearchRepositories
class SpringSecurityApplication

fun main(args: Array<String>) {
    runApplication<SpringSecurityApplication>(*args)
}
