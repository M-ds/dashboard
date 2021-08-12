package polar.bear.dashboard.person.verifytoken.usecase

import java.util.UUID
import polar.bear.dashboard.person.domain.TokenId

interface VerifyTokenUseCase {

    fun verify(request: Request): Response
    data class Request(val token: String)
    data class Response(val valid: Boolean, val tokenId: UUID? = null)

    fun regenerate(regenerateRequest: RegenerateRequest): RegenerateResponse
    data class RegenerateRequest(val tokenId: TokenId, val baseSiteUrl: String)
    data class RegenerateResponse(val success: Boolean)
}