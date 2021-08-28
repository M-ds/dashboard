package polar.bear.dashboard.person.signin.usecase

import polar.bear.dashboard.person.signin.domain.SignInPerson

interface SignInUseCase {

    fun signIn(request: SignInRequest): SignInResponse
    data class SignInRequest(val username: String, val password: String)
    data class SignInResponse(val signInPerson: SignInPerson?, val valid: Boolean, val errorMessage: String)
}