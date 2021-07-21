package polar.bear.dashboard.person.signup.infra

import polar.bear.dashboard.person.signup.domain.Token

interface TokenRepository {

    fun saveToken(token: Token): Boolean

}