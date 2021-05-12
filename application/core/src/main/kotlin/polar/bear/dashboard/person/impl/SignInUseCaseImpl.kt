package polar.bear.dashboard.person.impl

import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import polar.bear.dashboard.person.domain.PersonDetail
import polar.bear.dashboard.person.domain.SignInPerson
import polar.bear.dashboard.person.usecase.SignInUseCase
import polar.bear.dashboard.util.jwt.JwtUtil

class SignInUseCaseImpl(
    private val authenticationManager: AuthenticationManager,
    private val jwtUtil: JwtUtil
): SignInUseCase {

    override fun signIn(request: SignInUseCase.SignInRequest): SignInUseCase.SignInResponse {
        val username = request.username
        val password = request.password

        val authentication = authenticationManager.authenticate(
            UsernamePasswordAuthenticationToken(username, password)
        )

        val jwtToken = jwtUtil.generateToken(authentication.name)
        val personDetail = authentication.principal as PersonDetail

        val roles = personDetail.role.map { role ->
            role.name
        }.toList()

        val signInPerson = SignInPerson(
            token = jwtToken,
            username = personDetail.username,
            roles = roles
        )

        return SignInUseCase.SignInResponse(
            signInPerson = signInPerson
        )
    }
}