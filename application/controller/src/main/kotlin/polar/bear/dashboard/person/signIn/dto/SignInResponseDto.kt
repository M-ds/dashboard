package polar.bear.dashboard.person.signIn.dto

import polar.bear.dashboard.person.signin.domain.SignInPerson
import java.util.UUID

data class SignInResponseDto(
    val id: UUID,
    val token: String,
    val username: String,
    val roles: List<String>
) {
    companion object {
        fun from(
            signInPerson: SignInPerson
        ): SignInResponseDto {
            return SignInResponseDto(
                id = signInPerson.id,
                token = signInPerson.token,
                username = signInPerson.username,
                roles = signInPerson.roles
            )
        }
    }
}
