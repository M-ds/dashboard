package polar.bear.dashboard.repository.person

import polar.bear.dashboard.person.auth.domain.Person
import polar.bear.dashboard.person.auth.domain.PersonDetail
import polar.bear.dashboard.person.auth.domain.Role
import polar.bear.dashboard.person.infra.PersonRepository
import polar.bear.dashboard.person.verifytoken.domain.PersonRegisteredSuccess
import java.util.Optional
import java.util.UUID

class MockPersonRepository : PersonRepository {

    private var personDetail: Optional<PersonDetail> = Optional.empty()
    private var usernameExists: Boolean = false
    private var personId: UUID = UUID.randomUUID()

    fun loadUser(personDetail: PersonDetail?) {
        if (personDetail == null || personDetail.roles.isEmpty()) {
            this.personDetail = Optional.empty()
        } else {
            this.personDetail = Optional.of(personDetail)
        }
    }

    override fun loadUserByUsername(username: String): Optional<PersonDetail> {
        return this.personDetail
    }

    fun setUsernameExits(exists: Boolean) {
        this.usernameExists = exists
    }

    override fun usernameExists(username: String): Boolean {
        return this.usernameExists
    }

    override fun emailExits(email: String): Boolean {
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