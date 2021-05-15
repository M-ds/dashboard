package polar.bear.dashboard.person.impl

import polar.bear.dashboard.exception.InvalidUserId
import polar.bear.dashboard.person.domain.PersonProfile
import polar.bear.dashboard.person.infra.PersonRepository
import polar.bear.dashboard.person.usecase.PersonProfileUseCase

class PersonProfileUseCaseImpl(
    private val personRepository: PersonRepository
) : PersonProfileUseCase {

    override fun getPersonProfile(personId: String): PersonProfile {
        val convertedUserId = personId.toIntOrNull() ?: throw InvalidUserId("UserId is null. Sent userId = $personId")

        val optionalUserProfile = personRepository.getPersonProfile(convertedUserId)
        return if (optionalUserProfile.isPresent) {
            optionalUserProfile.get()
        } else {
            PersonProfile("", "", "")
        }
    }
}