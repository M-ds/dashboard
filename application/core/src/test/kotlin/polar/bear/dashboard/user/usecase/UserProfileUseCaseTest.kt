package polar.bear.dashboard.user.usecase

import org.junit.jupiter.api.Test
import polar.bear.dashboard.impl.UserProfileUseCaseImpl
import polar.bear.dashboard.repository.user.MockUserRepository
import polar.bear.dashboard.user.domain.UserProfile
import polar.bear.dashboard.user.usecase.UserProfileUseCaseTestSetup.userProfileUseCaseImpl
import polar.bear.dashboard.utils.Marker.GIVEN
import polar.bear.dashboard.utils.Marker.THEN
import polar.bear.dashboard.utils.Marker.WHEN

internal class UserProfileUseCaseTest {

    private val mockUserRepository = UserProfileUseCaseTestSetup.mockUserRepository
    private val underTest = userProfileUseCaseImpl()
    private val userProfile = UserProfile("TestUser", "TestPassword", "Test@mail.com")

    @Test
    fun getUserProfile() {
        GIVEN
        mockUserRepository.getUser(userProfile)
        val expectedResult = userProfile

        WHEN
        val result = underTest.getUserProfile("1")

        THEN
        assert(expectedResult == result)

    }
}

object UserProfileUseCaseTestSetup {
    val mockUserRepository = MockUserRepository()

    fun userProfileUseCaseImpl(): UserProfileUseCase {
        return UserProfileUseCaseImpl(mockUserRepository)
    }
}