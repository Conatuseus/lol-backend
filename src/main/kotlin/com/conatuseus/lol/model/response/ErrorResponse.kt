package com.conatuseus.lol.model.response

import com.fasterxml.jackson.annotation.JsonValue

interface ErrorResponse {
    val code: ErrorCode
    val msg: String?
}

data class CommonErrorResponse(
    override val code: ErrorCode,
    override val msg: String?
) : ErrorResponse

enum class ErrorCode(@JsonValue val code: String) {
    INTERNAL_ERROR("E1001"),

    // validation errors
    INVALID_REQUEST_FORMAT("E2001"),
    PASSWORD_NOT_SAME("E2002"),
    INVALID_PASSWORD_FORMAT("E2003"),
    INVALID_LOGIN_ID_FORMAT("E2004"),
    INVALID_NICKNAME_FORMAT("E2005"),
    INVALID_EMAIL_FORMAT("E2006")
}
