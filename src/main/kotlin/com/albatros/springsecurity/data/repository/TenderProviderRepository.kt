package com.albatros.springsecurity.data.repository

import com.albatros.springsecurity.data.model.database.TenderProvider
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TenderProviderRepository : JpaRepository<TenderProvider, Long>
