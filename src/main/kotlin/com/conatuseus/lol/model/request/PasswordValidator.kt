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
    companion object {
        const val LOGIN_ID_PATTERN = "(?i)^(?=.*[a-z])[a-z0-9]{8,20}\$"
        const val PASSWORD_PATTERN = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,20}$"
        const val NICKNAME_PATTERN = "^[가-힣a-zA-Z0-9]{2,10}\$"
        const val EMAIL_PATTERN = "(?:[a-z0-9!#\$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#\$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")" +
            "@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}" +
            "(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)])"
    }

    override fun isValid(
        value: SignupRequest,
        context: ConstraintValidatorContext
    ): Boolean {
        // 비밀번호 동일 검증
        if (value.isNotPasswordSame()) {
            throw PasswordNotSameException()
        }
        // login id pattern 검증
        if (isInvalidLoginId(value.loginId)) {
            throw InvalidLoginIdFormatException()
        }
        // password pattern 검증
        if (isInvalidPassword(value.password)) {
            throw InvalidPasswordFormatException()
        }
        // nickname pattern 검증
        if (isInvalidNickname(value.nickname)) {
            throw InvalidNicknameFormatException()
        }
        // email pattern 검증
        if (isInvalidEmail(value.email)) {
            throw InvalidEmailFormatException()
        }

        return true
    }

    private fun isInvalidLoginId(loginId: String): Boolean =
        !loginId.matches(LOGIN_ID_PATTERN.toRegex())

    private fun isInvalidPassword(password: String): Boolean =
        !password.matches(PASSWORD_PATTERN.toRegex())

    private fun isInvalidNickname(nickName: String): Boolean =
        !nickName.matches(NICKNAME_PATTERN.toRegex())

    private fun isInvalidEmail(email: String?): Boolean =
        email?.let { !it.matches(EMAIL_PATTERN.toRegex()) } ?: true
}

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
@Constraint(validatedBy = [PasswordValidator::class])
annotation class PasswordConstraint(
    val message: String = "Password is invalid. ",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = []
)

class InvalidLoginIdFormatException(
    code: ErrorCode = ErrorCode.INVALID_LOGIN_ID_FORMAT,
    message: String = "Invalid loginId format. "
) : ConstraintDeclarationException(code, message)

class PasswordNotSameException(
    code: ErrorCode = ErrorCode.PASSWORD_NOT_SAME,
    message: String = "Passwords are not same. "
) : ConstraintDeclarationException(code, message)

class InvalidPasswordFormatException(
    code: ErrorCode = ErrorCode.INVALID_PASSWORD_FORMAT,
    message: String = "Invalid password format. "
) : ConstraintDeclarationException(code, message)

class InvalidNicknameFormatException(
    code: ErrorCode = ErrorCode.INVALID_NICKNAME_FORMAT,
    message: String = "Invalid nickname format. "
) : ConstraintDeclarationException(code, message)

class InvalidEmailFormatException(
    code: ErrorCode = ErrorCode.INVALID_EMAIL_FORMAT,
    message: String = "Invalid email format. "
) : ConstraintDeclarationException(code, message)
