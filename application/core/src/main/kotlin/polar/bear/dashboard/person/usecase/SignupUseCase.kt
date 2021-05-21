package polar.bear.dashboard.person.usecase

interface SignupUseCase {

    fun signup(request: Request): Response
    data class Request(val username: String, val email: String, val password: String, val repeatedPassword: String)
    data class Response(val success: Boolean, val message: String)
}