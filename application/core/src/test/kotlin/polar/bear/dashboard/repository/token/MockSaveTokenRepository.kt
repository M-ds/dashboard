package polar.bear.dashboard.repository.token

import polar.bear.dashboard.person.signup.domain.Token
import polar.bear.dashboard.person.signup.infra.SaveTokenRepository

class MockSaveTokenRepository : SaveTokenRepository {

    private var saved: Boolean = false

    override fun saveToken(token: Token): Boolean {
        return this.saved
    }

    fun setSaveToken(saved: Boolean) {
        this.saved = saved
    }
}