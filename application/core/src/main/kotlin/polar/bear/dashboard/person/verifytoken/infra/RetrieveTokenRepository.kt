package polar.bear.dashboard.person.verifytoken.infra

import java.util.UUID
import polar.bear.dashboard.person.verifytoken.domain.RegisteredPerson

interface RetrieveTokenRepository {

    fun retrieveToken(tokenId: UUID): RegisteredPerson

}