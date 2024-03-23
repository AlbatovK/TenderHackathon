package com.albatros.springsecurity.data.model.dto

data class TenderInfoDtoItem(
    val ApiContractInfo: String,
    val ApiIzmInfo: String,
    val ApiPredInfo: String,
    val ApiProtokolInfo: String,
    val Category: String,
    val Customer: String,
    val CustomerINN: String,
    val Date: String,
    val EisLink: String,
    val EisLinkPrint: String,
    val EndTime: String,
    val Etp: String,
    val EtpLink: String,
    val Fz: String,
    val ID: String,
    val Info: String,
    val ObPrice: String,
    val ObPriceZ: String,
    val PlanZakupApiInfo: String,
    val PlanZakupNumber: String,
    val Price: String,
    val Region: String,
    val TenderLink: String,
    val TenderLinkEtp: String,
    val TenderLinkInner: String,
    val TenderName: String,
    val TenderNumOuter: String,
    val TenderType: String,
    val User_id: String,
    val biddingDate: String,
    val contactEmail: String,
    val contactName: String,
    val contactPhone: String,
    val deliveryPlace: String,
    val docsXML: List<Any>,
    val productsXML: List<Any>,
    val zakazLink: String,
    val zakazTendersLink: String
)