package polar.bear.dashboard.person.usecase

import polar.bear.dashboard.person.domain.PersonProfile

interface PersonProfileUseCase {

    fun getPersonProfile(personId: String): PersonProfile
}