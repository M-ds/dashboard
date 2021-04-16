package polar.bear.dashboard.user.usecase

import polar.bear.dashboard.user.domain.UserProfile

interface UserProfileUseCase {

    fun getUserProfile(userId: String): UserProfile
}