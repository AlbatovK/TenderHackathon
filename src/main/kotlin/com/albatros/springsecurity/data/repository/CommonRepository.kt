package com.albatros.springsecurity.data.repository

import com.albatros.springsecurity.data.model.database.AbstractEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.NoRepositoryBean

@NoRepositoryBean
interface CommonRepository<T : AbstractEntity> : JpaRepository<T, Long> {
    fun findEntityById(id: Long): T?
}
