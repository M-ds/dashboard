package polar.bear.dashboard.person.profile.infra

import java.util.Optional
import java.util.UUID
import polar.bear.dashboard.person.profile.domain.PersonProfile

interface PersonProfileRepository {
    fun getPersonProfile(personId: UUID): Optional<PersonProfile>
}