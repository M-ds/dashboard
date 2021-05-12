package polar.bear.dashboard.repository.user

import polar.bear.dashboard.person.domain.PersonProfile
import polar.bear.dashboard.person.infra.PersonRepository
import java.util.*

class MockPersonRepository : PersonRepository {

    private var personProfile: Optional<PersonProfile> = Optional.empty()

    fun getUser(personProfile: PersonProfile?) {
        if (personProfile == null) {
            this.personProfile = Optional.empty()
        } else {
            this.personProfile = Optional.of(personProfile)
        }
    }

    override fun loadUserByUsername(username: String) {
        TODO("Not yet implemented")
    }

    override fun getPersonProfile(personId: Int): Optional<PersonProfile> {
        return this.personProfile
    }
}