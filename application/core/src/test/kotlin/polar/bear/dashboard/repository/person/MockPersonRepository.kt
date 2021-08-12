package polar.bear.dashboard.repository.person

import java.util.UUID
import polar.bear.dashboard.person.auth.domain.Person
import polar.bear.dashboard.person.auth.domain.Role
import polar.bear.dashboard.person.domain.TokenId
import polar.bear.dashboard.person.infra.PersonRepository
import polar.bear.dashboard.person.verifytoken.domain.PersonRegisteredSuccess

class MockPersonRepository : PersonRepository {

    private var usernameExists: Boolean = false
    private var personId: UUID = UUID.randomUUID()

    fun setUsernameExits(exists: Boolean) {
        this.usernameExists = exists
    }

    override fun usernameExists(username: String): Boolean {
        return this.usernameExists
    }

    override fun emailExits(email: String): Boolean {
        TODO("Not yet implemented")
    }

    override fun getUsernameAndEmail(tokenId: TokenId): PersonRepository.UsernameAndEmailResponse {
        TODO("Not yet implemented")
    }

    fun setPersonIdForUsername(personId: UUID) {
        this.personId = personId
    }

    override fun getPersonIdFromUsername(username: String): UUID {
        return this.personId
    }

    override fun save(person: Person): Boolean {
        TODO("Not yet implemented")
    }

    override fun getRoleId(role: Role): UUID {
        TODO("Not yet implemented")
    }

    override fun successfulRegistered(personRegistered: PersonRegisteredSuccess) {
        TODO("Not yet implemented")
    }
}