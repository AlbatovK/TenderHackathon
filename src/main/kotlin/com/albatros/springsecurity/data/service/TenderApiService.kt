package com.albatros.springsecurity.data.service

import com.albatros.springsecurity.data.model.dto.TenderDto
import com.albatros.springsecurity.data.model.dto.TenderInfoDto
import com.albatros.springsecurity.data.model.dto.TenderProviderDto

interface TenderApiService {

    fun getAllTenderProviders(mode: String = "eauc"): MutableList<TenderProviderDto>?

    fun getTendersByProviders(providerId: Int): List<TenderDto>?

    fun getTenderInfoById(tenderId: Int): MutableList<TenderInfoDto>?
}
