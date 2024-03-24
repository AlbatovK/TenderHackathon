package com.albatros.springsecurity.data.model.database

import com.albatros.springsecurity.data.model.dto.TenderDto
import org.springframework.data.annotation.Id
import org.springframework.data.elasticsearch.annotations.Document
import org.springframework.data.elasticsearch.annotations.Field
import org.springframework.data.elasticsearch.annotations.FieldType

@Document(indexName = "tenders", createIndex = true)
class Tender(
    @Field(type = FieldType.Text, name = "api_tender_info")
    var apiTenderInfo: String?,
    @Field(type = FieldType.Keyword, name = "category")
    var category: String?,
    @Field(type = FieldType.Keyword, name = "customer")
    var customer: String?,
    @Field(type = FieldType.Text, name = "date")
    var date: String?,
    @Field(type = FieldType.Text, name = "end_time")
    var endTime: String?,
    @Field(type = FieldType.Text, name = "etp")
    var etp: String?,
    @Field(type = FieldType.Text, name = "fz")
    var fz: String?,
    @Field(type = FieldType.Text, name = "tender_id")
    @Id
    var tenderId: String?,
    @Field(type = FieldType.Long, name = "price")
    var price: String?,
    @Field(type = FieldType.Text, name = "region")
    var region: String?,
    @Field(type = FieldType.Keyword, name = "tender_link")
    var tenderLink: String?,
    @Field(type = FieldType.Keyword, name = "tender_inner_link")
    var tenderInnerLink: String?,
    @Field(type = FieldType.Text, name = "tender_name")
    var tenderName: String?,
    @Field(type = FieldType.Keyword, name = "tender_num_outer")
    var tenderNumOuter: String?,
    @Field(type = FieldType.Text, name = "user_id")
    var userId: String?,
    @Field(type = FieldType.Text, name = "meta_data")
    var metaData: String?
)

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
        userId = this.User_id,
        metaData = this.searchFragmentXML?.fragment?.joinToString() ?: ""
    )
