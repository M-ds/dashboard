package polar.bear.dashboard.person.usecase

import polar.bear.dashboard.person.domain.SignInPerson

interface SignInUseCase {

    fun signIn(request: SignInRequest): SignInResponse

    data class SignInRequest(val username: String, val password: String)
    data class SignInResponse(val signInPerson: SignInPerson?, val valid: Boolean, val errorMessage: String)
}