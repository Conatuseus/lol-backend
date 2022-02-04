package com.conatuseus.lol.exception

import com.conatuseus.lol.model.response.CommonErrorResponse
import com.conatuseus.lol.model.response.ErrorCode
import com.fasterxml.jackson.core.JsonParseException
import org.springframework.http.HttpStatus
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.HttpRequestMethodNotSupportedException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException

@RestControllerAdvice
class ErrorHandlingAdvice {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(Exception::class)
    fun exception(e: Exception) =
        CommonErrorResponse(ErrorCode.INTERNAL_ERROR, "Unknown error has occurred.")

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BadRequestException::class)
    fun handleBadRequestException(e: BadRequestException) =
        CommonErrorResponse(code = e.code, msg = e.message)

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(UnauthorizedException::class)
    fun handleUnauthorizedException(e: UnauthorizedException) =
        CommonErrorResponse(code = e.code, msg = e.message)

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(ForbiddenException::class)
    fun handleForbiddenException(e: ForbiddenException) =
        CommonErrorResponse(code = e.code, msg = e.message)

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InternalServerErrorException::class)
    fun handleInternalServerErrorException(e: InternalServerErrorException) =
        CommonErrorResponse(code = ErrorCode.INTERNAL_ERROR, msg = e.message)

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentTypeMismatchException::class)
    fun handleTypeMismatchException(e: MethodArgumentTypeMismatchException) =
        CommonErrorResponse(code = ErrorCode.INVALID_REQUEST_FORMAT, msg = "Invalid request format. ")

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleMethodArgumentNotValidException(e: MethodArgumentNotValidException) =
        CommonErrorResponse(code = ErrorCode.INVALID_REQUEST_FORMAT, msg = e.bindingResult.allErrors.firstOrNull()?.defaultMessage)

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException::class)
    fun handleIllegalArgumentException(e: IllegalArgumentException) =
        CommonErrorResponse(code = ErrorCode.INVALID_REQUEST_FORMAT, e.message)

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(JsonParseException::class)
    fun handleJsonParseException(e: JsonParseException) =
        CommonErrorResponse(code = ErrorCode.INVALID_REQUEST_FORMAT, msg = "Invalid request format. ")

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException::class)
    fun handleHttpMessageNotReadableException(e: HttpMessageNotReadableException) =
        CommonErrorResponse(code = ErrorCode.INVALID_REQUEST_FORMAT, msg = "Invalid request format. ")

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpRequestMethodNotSupportedException::class)
    fun handleHttpRequestMethodNotSupportedException(e: HttpRequestMethodNotSupportedException) =
        CommonErrorResponse(code = ErrorCode.INVALID_REQUEST_FORMAT, msg = e.message)

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintDeclarationException::class)
    fun handleConstraintDeclarationException(e: ConstraintDeclarationException) =
        CommonErrorResponse(code = e.code, msg = e.message)
}
