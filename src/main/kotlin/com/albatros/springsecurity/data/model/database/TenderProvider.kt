package com.albatros.springsecurity.data.model.database

import com.albatros.springsecurity.data.model.dto.TenderProviderDto
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import org.springframework.validation.annotation.Validated

@Entity
@Validated
class TenderProvider(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var tenderId: Int,
    var etpName: String,
    var etpLink: String
)

fun TenderProviderDto.toDomainObject() =
    TenderProvider(
        tenderId = this.id,
        etpName = this.etpName,
        etpLink = this.etpLink
    )
