package polar.bear.dashboard.person.signup.dto

import polar.bear.dashboard.person.signup.domain.SignupResponse

data class SignupResponseDto(
    val successMessage: String
) {
    companion object {
        fun fromDomain(signupResponse: SignupResponse): SignupResponseDto {
            return SignupResponseDto (
                successMessage = signupResponse.message
            )
        }
    }
}
