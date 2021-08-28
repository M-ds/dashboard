package polar.bear.dashboard.repository.token

import polar.bear.dashboard.person.verifytoken.domain.UpdateToken
import polar.bear.dashboard.person.verifytoken.infra.UpdateTokenRepository

class MockUpdateTokenRepository : UpdateTokenRepository {

    private var updated: Boolean = false

    override fun updateToken(updateToken: UpdateToken): Boolean {
        return updated
    }

    fun setUpdated(updated: Boolean) {
        this.updated = updated
    }
}