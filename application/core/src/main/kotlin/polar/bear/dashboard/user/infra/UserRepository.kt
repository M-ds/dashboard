package polar.bear.dashboard.user.infra

import polar.bear.dashboard.user.domain.UserProfile

interface UserRepository {

    fun getUserProfile(userId: Int): UserProfile
}