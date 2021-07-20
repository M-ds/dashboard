package polar.bear.dashboard.person.usecase

import polar.bear.dashboard.person.domain.PersonProfile
import java.util.UUID

interface PersonProfileUseCase {

    fun getPersonProfile(personId: UUID): PersonProfile
}