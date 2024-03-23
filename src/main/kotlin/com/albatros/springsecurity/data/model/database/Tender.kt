package com.albatros.springsecurity.data.model.database

import com.albatros.springsecurity.data.model.dto.TenderDto
import jakarta.persistence.Entity
import org.springframework.validation.annotation.Validated

@Entity
@Validated
class Tender(
    var apiTenderInfo: String?,
    var category: String?,
    var customer: String?,
    var date: String?,
    var endTime: String?,
    var etp: String?,
    var fz: String?,
    var tenderId: String?,
    var price: String?,
    var region: String?,
    var tenderLink: String?,
    var tenderInnerLink: String?,
    var tenderName: String?,
    var tenderNumOuter: String?,
    var userId: String?,
) : AbstractEntity()

fun TenderDto.toDomainObject() =
    Tender(
        apiTenderInfo = this.ApiTenderInfo,
        category = this.Category,
        customer = this.Customer,
        date = this.Date,
        endTime = this.EndTime,
        etp = this.Etp,
        fz = this.Fz,
        tenderId = this.ID,
        price = this.Price,
        region = this.Region,
        tenderLink = this.TenderLink,
        tenderInnerLink = this.TenderLinkInner,
        tenderName = this.TenderName,
        tenderNumOuter = this.TenderNumOuter,
        userId = this.User_id
    )
