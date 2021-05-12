package polar.bear.dashboard.person.usecase

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import polar.bear.dashboard.person.impl.UserProfileUseCaseImpl
import polar.bear.dashboard.repository.user.MockPersonRepository
import polar.bear.dashboard.person.domain.PersonProfile
import polar.bear.dashboard.person.usecase.UserProfileUseCaseTestSetup.userProfileUseCaseImpl
import polar.bear.dashboard.utils.Marker.GIVEN
import polar.bear.dashboard.utils.Marker.THEN
import polar.bear.dashboard.utils.Marker.WHEN

internal class PersonProfileUseCaseTest {

    private val mockUserRepository = UserProfileUseCaseTestSetup.mockUserRepository
    private val underTest = userProfileUseCaseImpl()
    private val userProfile = PersonProfile("TestUser", "TestPassword", "Test@mail.com")
    private val emptyUserProfile = PersonProfile("", "", "")

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
    val mockUserRepository = MockPersonRepository()

    fun userProfileUseCaseImpl(): UserProfileUseCase {
        return UserProfileUseCaseImpl(mockUserRepository)
    }
}