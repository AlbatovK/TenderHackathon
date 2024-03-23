package com.albatros.springsecurity.data.model.exception

import org.springframework.http.HttpStatus

class NotFoundException : AbstractApiException() {
    override val message: String
        get() = "Not found"
    override val status: HttpStatus
        get() = HttpStatus.NOT_FOUND
}
