package polar.bear.dashboard.person.impl

import polar.bear.dashboard.exception.InvalidUserId
import polar.bear.dashboard.person.domain.PersonProfile
import polar.bear.dashboard.person.infra.PersonRepository
import polar.bear.dashboard.person.usecase.PersonProfileUseCase
import java.util.UUID

class PersonProfileUseCaseImpl(
    private val personRepository: PersonRepository
) : PersonProfileUseCase {

    override fun getPersonProfile(personId: UUID): PersonProfile {
        val optionalUserProfile = personRepository.getPersonProfile(personId)
        return if (optionalUserProfile.isPresent) {
            optionalUserProfile.get()
        } else {
            PersonProfile("", "", "")
        }
    }
}