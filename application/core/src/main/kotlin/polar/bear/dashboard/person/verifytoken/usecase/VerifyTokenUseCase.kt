package polar.bear.dashboard.person.verifytoken.usecase

interface VerifyTokenUseCase {

    fun verify(request: Request): Response
    data class Request(val token: String)
    data class Response(val valid: Boolean, val message: String)
}