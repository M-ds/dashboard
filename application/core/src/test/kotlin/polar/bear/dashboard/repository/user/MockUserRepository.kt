package polar.bear.dashboard.repository.user

import polar.bear.dashboard.user.domain.UserProfile
import polar.bear.dashboard.user.infra.UserRepository
import java.util.*

class MockUserRepository : UserRepository {

    private var userProfile: Optional<UserProfile> = Optional.empty()

    fun getUser(userProfile: UserProfile?) {
        if (userProfile == null) {
            this.userProfile.isEmpty
        } else {
            this.userProfile = Optional.of(userProfile)
        }
    }

    override fun getUserProfile(userId: Int): Optional<UserProfile> {
        return this.userProfile
    }
}