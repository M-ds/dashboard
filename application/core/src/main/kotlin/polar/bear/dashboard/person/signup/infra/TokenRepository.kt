package polar.bear.dashboard.person.signup.infra

import java.util.UUID
import polar.bear.dashboard.person.signup.domain.Token

interface TokenRepository {

    fun saveToken(token: Token): Boolean
    fun retreiveToken(tokenId: UUID): Token
}