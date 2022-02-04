package com.conatuseus.lol.exception

import com.conatuseus.lol.model.response.ErrorCode
import javax.validation.ConstraintDeclarationException

open class BadRequestException(val code: ErrorCode, message: String) : RuntimeException(message)

open class UnauthorizedException(val code: ErrorCode, message: String) : RuntimeException(message)

open class ForbiddenException(val code: ErrorCode, message: String) : RuntimeException(message)

open class InternalServerErrorException(message: String?) : RuntimeException(message)

open class ConstraintDeclarationException(val code: ErrorCode, message: String) : ConstraintDeclarationException(message)
