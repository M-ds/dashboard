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
    private var emailExists: Boolean = false
    private var roleId: UUID = UUID.randomUUID()
    private var successfulSave: Boolean = false

    fun setUsernameExits(exists: Boolean) {
        this.usernameExists = exists
    }

    override fun usernameExists(username: String): Boolean {
        return this.usernameExists
    }

    override fun emailExits(email: String): Boolean {
        return emailExists
    }

    fun setEmailExists(exists: Boolean) {
        this.emailExists = exists
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
        return successfulSave
    }

    fun setSuccessFulSave(savedSuccess: Boolean) {
        this.successfulSave = savedSuccess
    }

    override fun getRoleId(role: Role): UUID {
        return this.roleId
    }

    override fun successfulRegistered(personRegistered: PersonRegisteredSuccess) {
        TODO("Not yet implemented")
    }
}