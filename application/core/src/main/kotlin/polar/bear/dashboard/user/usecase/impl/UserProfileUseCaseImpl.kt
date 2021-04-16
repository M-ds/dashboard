package polar.bear.dashboard.user.usecase.impl

import polar.bear.dashboard.user.domain.UserProfile
import polar.bear.dashboard.user.infra.UserRepository
import polar.bear.dashboard.user.usecase.UserProfileUseCase

class UserProfileUseCaseImpl(
    private val userRepository: UserRepository
) : UserProfileUseCase {

    override fun getUserProfile(userId: String): UserProfile {
        val convertedUserId = userId.toIntOrNull()
        val optionalUserProfile = userRepository.getUserProfile(convertedUserId!!)
        return if (optionalUserProfile.isPresent) {
            optionalUserProfile.get()
        } else {
            UserProfile("", "", "")
        }
    }
}