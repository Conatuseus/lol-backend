package com.conatuseus.lol.model.response

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonPropertyOrder
import com.fasterxml.jackson.annotation.JsonValue

interface Response {
    val status: Int
    val message: String?
}

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder("status", "message")
open class DefaultResponse(
    override val status: Int = Status.OK.status,
    override val message: String? = null,
) : Response

enum class Status(@JsonValue val status: Int) {
    UNKNOWN(-1),
    OK(0),
    BAD_REQUEST(400),
    UNAUTHORIZED(401),
    FORBIDDEN(403),
    NOT_FOUND(404),
    ERROR(500)
    ;

    companion object {
        @JvmStatic
        @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
        fun of(status: Int, defaultStatus: Status = UNKNOWN): Status {
            return values().find { it.status == status } ?: defaultStatus
        }
    }
}
