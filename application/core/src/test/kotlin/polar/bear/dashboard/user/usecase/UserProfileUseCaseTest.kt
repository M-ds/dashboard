package polar.bear.dashboard.user.usecase

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
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
    private val emptyUserProfile = UserProfile("", "", "")

    @Test
    fun `Happy flow of getting a user profile`() {
        GIVEN
        mockUserRepository.getUser(userProfile)
        val expectedResult = userProfile

        WHEN
        val result = underTest.getUserProfile("1")

        THEN
        assert(expectedResult == result)
    }

    @Test
    fun `No Userprofile is found, thus empty result is returned`() {
        GIVEN
        mockUserRepository.getUser(emptyUserProfile)
        val expectedResult = emptyUserProfile

        WHEN
        val result = underTest.getUserProfile("100")

        THEN
        assert(expectedResult == result)
    }

    @Test
    fun `Error is thrown because of invalid userId`() {
        WHEN
        assertThrows<NullPointerException> {
            underTest.getUserProfile("ABC")
        }
    }
}

object UserProfileUseCaseTestSetup {
    val mockUserRepository = MockUserRepository()

    fun userProfileUseCaseImpl(): UserProfileUseCase {
        return UserProfileUseCaseImpl(mockUserRepository)
    }
}