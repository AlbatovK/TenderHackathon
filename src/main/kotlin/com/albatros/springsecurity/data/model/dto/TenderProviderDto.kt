package com.albatros.springsecurity.data.model.dto

import com.fasterxml.jackson.annotation.JsonProperty
import java.io.Serializable

class TenderProviderDto(
    @JsonProperty("ID")
    var id: Int,
    @JsonProperty("EtpName")
    var etpName: String,
    @JsonProperty("EtpLink")
    var etpLink: String
) : Serializable
