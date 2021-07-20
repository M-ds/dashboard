package polar.bear.dashboard.person.usecase

import polar.bear.dashboard.person.domain.SignupResponse

interface SignupUseCase {

    fun signup(request: Request): Response
    data class Request(val username: String, val email: String, val password: String, val repeatedPassword: String)
    data class Response(val signupResponse: SignupResponse?, val valid: Boolean, val errorMessage: String)
}