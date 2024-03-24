package com.albatros.springsecurity.data.repository

import com.albatros.springsecurity.data.model.database.Tender
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository
import org.springframework.stereotype.Repository

@Repository
interface TenderRepository : ElasticsearchRepository<Tender, String> {
    fun findAllByTenderId(tenderId: String): List<Tender>

    fun findAllByCustomerContainsIgnoreCase(customer: String): List<Tender>

    fun findAllByRegionContainsIgnoreCase(region: String): List<Tender>

    fun findAllByCategory(category: String): List<Tender>

    fun findAllByEtpEqualsIgnoreCase(etp: String): List<Tender>

    fun findAllByTenderNameContainingIgnoreCase(query: String): List<Tender>
}
