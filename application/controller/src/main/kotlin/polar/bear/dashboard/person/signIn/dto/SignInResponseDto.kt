package polar.bear.dashboard.person.signIn.dto

import polar.bear.dashboard.person.domain.SignInPerson

data class SignInResponseDto(
    val username: String,
    val roles: List<String>
) {
    companion object {
        fun from(
            signInPerson: SignInPerson
        ): SignInResponseDto {
            return SignInResponseDto(
                username = signInPerson.username,
                roles = signInPerson.roles
            )
        }
    }
}
