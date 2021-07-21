package polar.bear.dashboard.person.signup.dto

import polar.bear.dashboard.person.signup.domain.SignupResponse

data class SignupResponseDto(
    val message: String
) {
    companion object {
        fun fromDomain(signupResponse: SignupResponse): SignupResponseDto {
            return SignupResponseDto (
                message = signupResponse.message
            )
        }
    }
}
