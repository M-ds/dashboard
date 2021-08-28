package polar.bear.dashboard.person.verifytoken.infra

import polar.bear.dashboard.person.verifytoken.domain.UpdateToken

interface UpdateTokenRepository {
    fun updateToken(updateToken: UpdateToken): Boolean
}