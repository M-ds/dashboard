package polar.bear.dashboard.person.verifytoken.domain

import java.time.LocalDateTime
import java.util.UUID
import polar.bear.dashboard.person.domain.TokenId

data class UpdateToken(
    val tokenId: TokenId,
    val newToken: UUID,
    val newExpirationDate: LocalDateTime
)