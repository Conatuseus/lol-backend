package com.conatuseus.lol.model.request

import com.conatuseus.lol.exception.ConstraintDeclarationException
import com.conatuseus.lol.model.response.ErrorCode
import org.springframework.stereotype.Component
import javax.validation.Constraint
import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext
import javax.validation.Payload
import kotlin.reflect.KClass

@Component
class PasswordValidator : ConstraintValidator<PasswordConstraint, SignupRequest> {
    override fun isValid(
        value: SignupRequest,
        context: ConstraintValidatorContext
    ): Boolean {
        // password pattern 검증 로직

        // 비밀번호 동일 검증 로직
        // Custom ErrorCode를 위해 throw
        if (value.isNotPasswordSame()) {
            throw PasswordNotSameException()
        }
        return true
    }
}

class PasswordNotSameException(
    code: ErrorCode = ErrorCode.PASSWORD_NOT_SAME,
    message: String = "Passwords are not same. "
) : ConstraintDeclarationException(code, message)

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
@Constraint(validatedBy = [PasswordValidator::class])
annotation class PasswordConstraint(
    val message: String = "Password is invalid. ",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = []
)
