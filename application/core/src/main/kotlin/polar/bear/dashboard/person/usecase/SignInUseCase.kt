package polar.bear.dashboard.person.usecase

interface SignInUseCase {

    fun signIn(request: SignInRequest): SignInResponse

    data class SignInRequest(val username: String, val password: String)
    data class SignInResponse(val token: String)
}