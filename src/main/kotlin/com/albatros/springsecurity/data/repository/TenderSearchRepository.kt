package com.albatros.springsecurity.data.repository

import com.albatros.springsecurity.data.model.database.Tender
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository
import org.springframework.stereotype.Repository

@Repository
interface TenderRepository : ElasticsearchRepository<Tender, String> {

    fun findAllByTenderId(tenderId: String, pageable: Pageable): Page<Tender>

    fun findAllByCustomerContainsIgnoreCase(customer: String, pageable: Pageable): Page<Tender>

    fun findAllByRegionContainsIgnoreCase(region: String, pageable: Pageable): Page<Tender>

    fun findAllByCategory(category: String, pageable: Pageable): Page<Tender>

    fun findAllByEtpEqualsIgnoreCase(etp: String, pageable: Pageable): Page<Tender>

    fun findAllByTenderNameContainingIgnoreCase(query: String, pageable: Pageable): Page<Tender>
}
