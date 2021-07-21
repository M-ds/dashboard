package polar.bear.dashboard.person.signup.usecase

import polar.bear.dashboard.person.signup.domain.SignupResponse

interface SignupUseCase {

    fun signup(request: Request): Response
    data class Request(val username: String, val email: String, val password: String, val repeatedPassword: String, val siteUrl: String)
    data class Response(val signupResponse: SignupResponse?, val valid: Boolean, val errorMessage: String)
}