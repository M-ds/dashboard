package polar.bear.dashboard.repository.person

import polar.bear.dashboard.person.verifytoken.domain.RegisteredPerson
import polar.bear.dashboard.person.verifytoken.infra.RetrieveTokenRepository
import java.util.UUID

class MockVerifyTokenRepository : RetrieveTokenRepository {

    private var registeredPerson: RegisteredPerson? = null

    override fun retrieveToken(tokenId: UUID): RegisteredPerson {
        return registeredPerson!!
    }

    fun setRegisteredPerson(registeredPerson: RegisteredPerson?) {
        this.registeredPerson = registeredPerson
    }
}