package polar.bear.dashboard.person.signup.usecase.impl

import org.springframework.security.crypto.password.PasswordEncoder
import polar.bear.dashboard.person.auth.domain.Person
import polar.bear.dashboard.person.auth.domain.Role
import polar.bear.dashboard.person.signup.domain.SignupResponse
import polar.bear.dashboard.person.infra.PersonRepository
import polar.bear.dashboard.person.signup.usecase.SignupUseCase
import java.time.LocalDate
import java.util.UUID
import polar.bear.dashboard.person.signup.domain.Token
import polar.bear.dashboard.person.signup.infra.SendEmail
import polar.bear.dashboard.person.signup.infra.SaveTokenRepository

class SignupUseCaseImpl(
    private val personRepository: PersonRepository,
    private val saveTokenRepository: SaveTokenRepository,
    private val passwordEncoder: PasswordEncoder,
    private val sendEmail: SendEmail
) : SignupUseCase {

    private val onlyNumbersOrCharactersRegex = "^[A-Za-z0-9]*\$".toRegex()

    override fun signup(request: SignupUseCase.Request): SignupUseCase.Response {

        val validRequest = validateRequest(request)
        if (!validRequest.valid) {
            return SignupUseCase.Response(
                signupResponse = null,
                valid = false,
                errorMessage = validRequest.errorMessage!!
            )
        }

        val usernameAlreadyExists = personRepository.usernameExists(request.username)
        if (usernameAlreadyExists) {
            return SignupUseCase.Response(
                signupResponse = null,
                valid = false,
                errorMessage = "Username already exists, please choose another."
            )
        }

        val emailAlreadyExists = personRepository.emailExits(request.email)
        if (emailAlreadyExists) {
            return SignupUseCase.Response(
                signupResponse = null,
                valid = false,
                errorMessage = "Email address is already in use, please choose another."
            )
        }

        val roleId = personRepository.getRoleId(Role.ROLE_MEMBER)
        val personId = UUID.randomUUID()

        val token = generateValidationToken(personId)
        val person = Person(
            id = personId,
            username = request.username,
            email = request.email,
            password = passwordEncoder.encode(request.password),
            tokenId = token.id,
            active = false,
            creationDate = LocalDate.now(),
            role = roleId
        )

        val personSuccessfulSaved = personRepository.save(person)
        val successfulSaveToken = saveTokenRepository.saveToken(token)

        if (successfulSaveToken && personSuccessfulSaved) {
            sendEmail.sendEmail(
                SendEmail.Request(
                    username = person.username,
                    email = person.email,
                    token = token.token,
                    verifyUrl = request.siteUrl
                )
            )
        }

        return if (successfulSaveToken && personSuccessfulSaved) {
            SignupUseCase.Response(
                signupResponse = SignupResponse(
                    message = "Person is successfully registered."
                ),
                valid = true,
                errorMessage = ""
            )
        } else {
            SignupUseCase.Response(
                signupResponse = null,
                valid = false,
                errorMessage = "Could not create a new user in the database.. Please contact a developer.."
            )
        }
    }

    private fun validateRequest(request: SignupUseCase.Request): ValidatedRequestResponse {
        val equalPasswords = checkPasswordForEquality(request.password, request.repeatedPassword)
        if (!equalPasswords) {
            return ValidatedRequestResponse(
                valid = false,
                errorMessage = "Passwords are not equal!"
            )
        }
        val isValidUsername = validUserName(request.username)
        if (!isValidUsername) {
            return ValidatedRequestResponse(
                valid = false,
                errorMessage = "Username has to be filled in!"
            )
        }
        val emailAddressIsNotEmpty = request.email.isNotBlank()
        if (!emailAddressIsNotEmpty) {
            return ValidatedRequestResponse(
                valid = false,
                errorMessage = "Email address has to be filled in!"
            )
        }
        return ValidatedRequestResponse(
            valid = true,
            errorMessage = null
        )
    }

    private fun checkPasswordForEquality(password: String, repeatedPassword: String): Boolean {
        return password == repeatedPassword
    }

    private fun validUserName(username: String): Boolean {
        if (username.isNotBlank()) {
            return onlyNumbersOrCharactersRegex.matches(username)
        }
        return false
    }

    private fun generateValidationToken(personId: UUID): Token {
        return Token.createToken(personId)
    }
}

data class ValidatedRequestResponse(
    val valid: Boolean,
    val errorMessage: String?
)