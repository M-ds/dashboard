package polar.bear.dashboard.repository.user

import polar.bear.dashboard.person.domain.PersonDetail
import polar.bear.dashboard.person.domain.PersonProfile
import polar.bear.dashboard.person.infra.PersonRepository
import java.util.Optional
import java.util.UUID

class MockPersonRepository : PersonRepository {

    private var personDetail: Optional<PersonDetail> = Optional.empty()
    private var personProfile: Optional<PersonProfile> = Optional.empty()
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

    fun setPersonIdForUsername(personId: UUID) {
        this.personId = personId
    }

    override fun getPersonIdFromUsername(username: String): UUID {
        return this.personId
    }

    fun getUser(personProfile: PersonProfile?) {
        if (personProfile == null) {
            this.personProfile = Optional.empty()
        } else {
            this.personProfile = Optional.of(personProfile)
        }
    }

    override fun getPersonProfile(personId: UUID): Optional<PersonProfile> {
        return this.personProfile
    }
}