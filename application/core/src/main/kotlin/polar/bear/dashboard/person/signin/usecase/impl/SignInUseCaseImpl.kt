package polar.bear.dashboard.person.signin.usecase.impl

import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import polar.bear.dashboard.person.auth.PersonDetails
import polar.bear.dashboard.person.signin.domain.SignInPerson
import polar.bear.dashboard.person.infra.PersonRepository
import polar.bear.dashboard.person.signin.usecase.SignInUseCase
import polar.bear.dashboard.util.jwt.JwtUtil

class SignInUseCaseImpl(
    private val authenticationManager: AuthenticationManager,
    private val jwtUtil: JwtUtil,
    private val personRepository: PersonRepository
) : SignInUseCase {

    override fun signIn(request: SignInUseCase.SignInRequest): SignInUseCase.SignInResponse {
        val username = request.username
        val password = request.password

        if (username.isBlank() || password.isBlank()) {
            return SignInUseCase.SignInResponse(
                signInPerson = null,
                valid = false,
                errorMessage = "Username and password should both be filled in, and can not be empty"
            )
        }

        val usernameExists = personRepository.usernameExists(username)
        if (!usernameExists) {
            return SignInUseCase.SignInResponse(
                signInPerson = null,
                valid = false,
                errorMessage = "Username with $username is not known. Please check username or register a new user!"
            )
        }

        val authentication = authenticationManager.authenticate(
            UsernamePasswordAuthenticationToken(username, password)
        )

        val jwtToken = jwtUtil.generateToken(authentication.name)
        val personDetail = authentication.principal as PersonDetails

        val roles = personDetail.authorities.map { role ->
            role.authority
        }.toList()

        val personId = personRepository.getPersonIdFromUsername(username)

        val signInPerson = SignInPerson(
            id = personId,
            token = jwtToken,
            username = personDetail.username,
            roles = roles
        )

        return SignInUseCase.SignInResponse(
            signInPerson = signInPerson,
            valid = true,
            errorMessage = ""
        )

    }
}