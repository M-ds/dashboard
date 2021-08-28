package polar.bear.dashboard.person.profile.usecase

import polar.bear.dashboard.person.profile.domain.PersonProfile
import java.util.UUID

interface PersonProfileUseCase {

    fun getPersonProfile(personId: UUID): PersonProfile
}