package polar.bear.dashboard.person.usecase

import org.junit.jupiter.api.Test
import polar.bear.dashboard.person.domain.PersonProfile
import polar.bear.dashboard.person.impl.PersonProfileUseCaseImpl
import polar.bear.dashboard.person.usecase.UserProfileUseCaseTestSetup.personProfileUseCase
import polar.bear.dashboard.repository.user.MockPersonRepository
import polar.bear.dashboard.utils.Marker.GIVEN
import polar.bear.dashboard.utils.Marker.THEN
import polar.bear.dashboard.utils.Marker.WHEN
import java.util.UUID

internal class PersonProfileUseCaseTest {

    private val mockUserRepository = UserProfileUseCaseTestSetup.mockPersonRepository
    private val underTest = personProfileUseCase()
    private val personProfile = PersonProfile("TestUser", "TestPassword", "Test@mail.com")
    private val emptyPersonProfile = PersonProfile("", "", "")
    private val knownUUID = UUID.randomUUID()
    private val unKnownUUID = UUID.randomUUID()

    @Test
    fun `Happy flow of getting a user profile`() {
        GIVEN
        mockUserRepository.getUser(personProfile)
        val expectedResult = personProfile

        WHEN
        val result = underTest.getPersonProfile(knownUUID)

        THEN
        assert(expectedResult == result)
    }

    @Test
    fun `No Userprofile is found, thus empty result is returned`() {
        GIVEN
        mockUserRepository.getUser(emptyPersonProfile)
        val expectedResult = emptyPersonProfile

        WHEN
        val result = underTest.getPersonProfile(unKnownUUID)

        THEN
        assert(expectedResult == result)
    }
}

object UserProfileUseCaseTestSetup {
    val mockPersonRepository = MockPersonRepository()

    fun personProfileUseCase(): PersonProfileUseCase {
        return PersonProfileUseCaseImpl(mockPersonRepository)
    }
}