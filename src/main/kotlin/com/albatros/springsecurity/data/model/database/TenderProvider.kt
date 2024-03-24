package com.albatros.springsecurity.data.model.database

import com.albatros.springsecurity.data.model.dto.TenderProviderDto
import jakarta.persistence.Entity
import org.springframework.validation.annotation.Validated

@Entity
@Validated
class TenderProvider(
    var tenderId: Int,
    var etpName: String,
    var etpLink: String
) : AbstractEntity()

fun TenderProviderDto.toDomainObject() =
    TenderProvider(
        tenderId = this.id,
        etpName = this.etpName,
        etpLink = this.etpLink
    )
