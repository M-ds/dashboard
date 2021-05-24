package polar.bear.dashboard.person.impl

import org.springframework.security.crypto.password.PasswordEncoder
import polar.bear.dashboard.person.domain.Person
import polar.bear.dashboard.person.domain.Role
import polar.bear.dashboard.person.domain.SignupResponse
import polar.bear.dashboard.person.infra.PersonRepository
import polar.bear.dashboard.person.usecase.SignupUseCase
import java.time.LocalDate
import java.util.UUID

class SignupUseCaseImpl(
    private val personRepository: PersonRepository,
    private val passwordEncoder: PasswordEncoder
) : SignupUseCase {

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
        val person = Person(
            id = UUID.randomUUID(),
            username = request.username,
            email = request.email,
            password = passwordEncoder.encode(request.password),
            token = "TODO_GENERATE_TOKEN",
            active = false,
            creationDate = LocalDate.now(),
            role = roleId
        )

        val success = personRepository.save(person)

        return if (success) {
            SignupUseCase.Response(
                signupResponse = SignupResponse(
                    message = "Person is successfully registered.",
                    emailToken = ""
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
        val usernameIsNotEmpty = request.username.isNotBlank()
        if (!usernameIsNotEmpty) {
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
}

data class ValidatedRequestResponse(
    val valid: Boolean,
    val errorMessage: String?
)