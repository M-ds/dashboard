package polar.bear.dashboard.person.usecase

import polar.bear.dashboard.person.domain.PersonProfile

interface UserProfileUseCase {

    fun getUserProfile(userId: String): PersonProfile
}