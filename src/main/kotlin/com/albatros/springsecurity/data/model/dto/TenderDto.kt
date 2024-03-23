package com.albatros.springsecurity.data.model.dto

data class TenderDtoItem(
    val ApiTenderInfo: String,
    val Category: String,
    val Customer: String,
    val Date: String,
    val EndTime: String,
    val Etp: String,
    val Fz: String,
    val ID: String,
    val Price: String,
    val Region: String,
    val TenderLink: String,
    val TenderLinkInner: String,
    val TenderName: String,
    val TenderNumOuter: String,
    val Total: String,
    val User_id: String,
    val searchFragmentXML: SearchFragmentXML
)