package polar.bear.dashboard.person.impl

import polar.bear.dashboard.person.domain.PersonProfile
import polar.bear.dashboard.person.infra.PersonRepository
import polar.bear.dashboard.person.usecase.UserProfileUseCase

class UserProfileUseCaseImpl(
    private val personRepository: PersonRepository
) : UserProfileUseCase {

    override fun getUserProfile(userId: String): PersonProfile {
        val convertedUserId = userId.toIntOrNull()
        val optionalUserProfile = personRepository.getPersonProfile(convertedUserId!!)
        return if (optionalUserProfile.isPresent) {
            optionalUserProfile.get()
        } else {
            PersonProfile("", "", "")
        }
    }
}