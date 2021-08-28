package polar.bear.dashboard.repository.person

import polar.bear.dashboard.person.profile.domain.PersonProfile
import polar.bear.dashboard.person.profile.infra.PersonProfileRepository
import java.util.Optional
import java.util.UUID

class MockPersonProfileRepository: PersonProfileRepository {

    private var personProfile: Optional<PersonProfile> = Optional.empty()

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