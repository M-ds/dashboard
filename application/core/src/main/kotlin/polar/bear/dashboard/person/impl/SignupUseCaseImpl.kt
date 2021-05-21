package polar.bear.dashboard.person.impl

import org.springframework.security.crypto.password.PasswordEncoder
import polar.bear.dashboard.person.domain.Person
import polar.bear.dashboard.person.domain.Role
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
                success = false,
                message = "Username already exists, please choose another."
            )
        }

        val emailAlreadyExists = personRepository.emailExits(request.email)
        if (emailAlreadyExists) {
            return SignupUseCase.Response(
                success = false,
                message = "Email address is already in use, please choose another."
            )
        }

        val person = Person(
            id = UUID.randomUUID(),
            username = request.username,
            email = request.email,
            password = passwordEncoder.encode(request.password),
            token = "TODO_GENERATE_TOKEN",
            active = false,
            creationDate = LocalDate.now(),
            role = Role.ROLE_MEMBER
        )

        personRepository.save(person)

        return SignupUseCase.Response(
            success = true,
            message = "Person is successfully registered."
        )

    }
}