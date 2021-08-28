package polar.bear.dashboard.person.domain

import java.util.UUID

data class TokenId(val value: UUID) {
    companion object {
        fun fromString(id: String): TokenId {
            return TokenId(UUID.fromString(id))
        }
    }
}