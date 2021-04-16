package polar.bear.dashboard.user.infra

import polar.bear.dashboard.user.domain.UserProfile
import java.util.*

interface UserRepository {

    fun getUserProfile(userId: Int): Optional<UserProfile>
}