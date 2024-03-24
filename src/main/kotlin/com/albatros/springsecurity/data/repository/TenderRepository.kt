package com.albatros.springsecurity.data.repository

import com.albatros.springsecurity.data.model.database.Tender
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository
import org.springframework.stereotype.Repository

@Repository
interface TenderRepository : ElasticsearchRepository<Tender, String>
