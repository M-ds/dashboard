package polar.bear.dashboard.repository.user

import polar.bear.dashboard.person.domain.PersonDetail
import polar.bear.dashboard.person.domain.PersonProfile
import polar.bear.dashboard.person.infra.PersonRepository
import java.util.Optional

class MockPersonRepository : PersonRepository {

    private var personDetail: Optional<PersonDetail> = Optional.empty()
    private var personProfile: Optional<PersonProfile> = Optional.empty()

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

    override fun usernameExists(username: String): Boolean {
        TODO("Not yet implemented")
    }

    override fun getPersonIdFromUsername(username: String): Int {
        TODO("Not yet implemented")
    }

    fun getUser(personProfile: PersonProfile?) {
        if (personProfile == null) {
            this.personProfile = Optional.empty()
        } else {
            this.personProfile = Optional.of(personProfile)
        }
    }

    override fun getPersonProfile(personId: Int): Optional<PersonProfile> {
        return this.personProfile
    }
}