package polar.bear.dashboard.person.signup.dto

import polar.bear.dashboard.person.domain.SignupResponse

data class SignupResponseDto(
    val message: String,
    val emailToken: String
) {
    companion object {
        fun fromDomain(signupResponse: SignupResponse): SignupResponseDto {
            return SignupResponseDto (
                message = signupResponse.message,
                emailToken = signupResponse.emailToken
            )
        }
    }
}
