package polar.bear.dashboard.user.usecase.impl

import org.springframework.stereotype.Service
import polar.bear.dashboard.user.domain.UserProfile
import polar.bear.dashboard.user.infra.UserRepository
import polar.bear.dashboard.user.usecase.UserProfileUseCase

@Service
class UserProfileUseCaseImpl(
    private val userRepository: UserRepository
) : UserProfileUseCase {

    override fun getUserProfile(userId: String): UserProfile {
        val convertedUserId = userId.toIntOrNull()
        return userRepository.getUserProfile(convertedUserId!!)
    }
}