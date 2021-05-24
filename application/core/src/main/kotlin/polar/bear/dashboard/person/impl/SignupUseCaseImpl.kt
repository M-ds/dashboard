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
}