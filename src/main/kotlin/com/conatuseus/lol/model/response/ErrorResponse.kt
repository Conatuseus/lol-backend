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
    PASSWORD_NOT_SAME("E2002")
}
