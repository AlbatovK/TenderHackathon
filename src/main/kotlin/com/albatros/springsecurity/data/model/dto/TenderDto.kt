package com.albatros.springsecurity.data.model.dto

import java.io.Serializable

class TenderDto(
    var ApiTenderInfo: String?,
    var Category: String?,
    var Customer: String?,
    var Date: String?,
    var EndTime: String?,
    var Etp: String?,
    var Fz: String?,
    var ID: String?,
    var Price: String?,
    var Region: String?,
    var TenderLink: String?,
    var TenderLinkInner: String?,
    var TenderName: String?,
    var TenderNumOuter: String?,
    var Total: String?,
    var User_id: String?,
    var searchFragmentXML: SearchFragmentXML?
): Serializable