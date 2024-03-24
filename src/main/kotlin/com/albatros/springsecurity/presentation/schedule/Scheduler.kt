package com.albatros.springsecurity.presentation.schedule

import com.albatros.springsecurity.data.model.database.toDomainObject
import com.albatros.springsecurity.data.model.dto.TenderDto
import com.albatros.springsecurity.data.model.dto.TenderProviderDto
import com.albatros.springsecurity.data.repository.TenderProviderRepository
import com.albatros.springsecurity.data.repository.TenderRepository
import com.albatros.springsecurity.data.service.TenderApiService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Configuration
import org.springframework.scheduling.annotation.Async
import org.springframework.scheduling.annotation.Scheduled

@Configuration
class Scheduler(
    private val tenderProviderRepository: TenderProviderRepository,
    private val tenderRepository: TenderRepository,
    private val apiService: TenderApiService,
) {

    private val logger: Logger = LoggerFactory.getLogger(Scheduler::class.java)

    @Async
    @Scheduled(fixedDelay = 100_000)
    fun scheduleTenderProvidersData() {
        logger.debug("Collecting providers data from external sources.")
        apiService.getAllTenderProviders()?.let {
            tenderProviderRepository.saveAll(
                it.map(TenderProviderDto::toDomainObject)
            )
        }

        logger.debug("Collection stopped.")
    }

    @Async
    @Scheduled(fixedDelay = 100_000)
    fun scheduleTendersData() {
        logger.debug("Collecting tenders data from external sources.")

        tenderProviderRepository.findAll().forEach { provider ->
            apiService.getTendersByProviders(provider.tenderId)?.let {
                tenderRepository.saveAll(
                    it.map(TenderDto::toDomainObject)
                )
            }
        }

        logger.debug("Collection stopped.")
    }
}
