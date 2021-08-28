package polar.bear.dashboard.person.profile.usecase

import java.util.UUID
import polar.bear.dashboard.person.profile.domain.PersonProfile
import polar.bear.dashboard.person.profile.infra.PersonProfileRepository

class PersonProfileUseCaseImpl(
    private val personProfileRepository: PersonProfileRepository
) : PersonProfileUseCase {

    override fun getPersonProfile(personId: UUID): PersonProfile {
        val optionalUserProfile = personProfileRepository.getPersonProfile(personId)
        return if (optionalUserProfile.isPresent) {
            optionalUserProfile.get()
        } else {
            PersonProfile("", "", "")
        }
    }
}