package polar.bear.dashboard.repository.token

import java.util.UUID
import polar.bear.dashboard.person.verifytoken.domain.RegisteredPerson
import polar.bear.dashboard.person.verifytoken.infra.RetrieveTokenRepository

class MockRetrieveTokenRepository : RetrieveTokenRepository {

    private var registeredPerson: RegisteredPerson? = null

    override fun retrieveToken(tokenId: UUID): RegisteredPerson {
        return registeredPerson!!
    }

    fun setRegsiteredPerson(registeredPerson: RegisteredPerson) {
        this.registeredPerson = registeredPerson
    }
}