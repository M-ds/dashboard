package polar.bear.dashboard.person.usecase

import org.junit.jupiter.api.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.authority.SimpleGrantedAuthority
import polar.bear.dashboard.person.auth.PersonDetails
import polar.bear.dashboard.person.domain.PersonDetail
import polar.bear.dashboard.person.domain.Role
import polar.bear.dashboard.person.impl.SignInUseCaseImpl
import polar.bear.dashboard.person.usecase.SingInUseCaseTestSetup.signInUseCase
import polar.bear.dashboard.repository.user.MockPersonRepository
import polar.bear.dashboard.util.jwt.JwtUtil
import polar.bear.dashboard.utils.Marker.GIVEN
import polar.bear.dashboard.utils.Marker.THEN
import polar.bear.dashboard.utils.Marker.WHEN
import java.util.UUID

internal class SignInUseCaseTest {

    private val mockPersonRepository = SingInUseCaseTestSetup.mockPersonRepository
    private val mockAuthenticationManager = SingInUseCaseTestSetup.mockAuthenticationManager
    private val mockAuthentication = SingInUseCaseTestSetup.mockAuthentication

    private val underTest = signInUseCase()

    private val regularPerson = PersonDetail(username = "testPerson", password = "test", isActive = true, mutableListOf(Role.ROLE_USER))
    private val uuid = UUID.fromString("dfa572fd-ffb9-4e23-b391-4f4ed5b3728e")

    @Test
    fun `Happy flow of a regular person sign in`() {
        GIVEN
        mockPersonRepository.setPersonIdForUsername(uuid)
        mockPersonRepository.setUsernameExits(true)
        val personDetails = PersonDetails(regularPerson)

        `when`(mockAuthenticationManager.authenticate(
            UsernamePasswordAuthenticationToken(personDetails.username, personDetails.password)
        )).thenReturn(mockAuthentication)

        `when`(mockAuthentication.principal).thenReturn(personDetails)
        `when`(mockAuthentication.name).thenReturn(personDetails.username)
        `when`(mockAuthentication.authorities).thenReturn(mutableListOf(SimpleGrantedAuthority(Role.ROLE_USER.name)))

        WHEN
        val result = underTest.signIn(SignInUseCase.SignInRequest(regularPerson.username, regularPerson.password))

        THEN
        assert(result.errorMessage.isEmpty())
        assert(result.valid)

        val signInPerson = result.signInPerson!!

        assert(signInPerson.id == uuid)
        assert(signInPerson.username == personDetails.username)
        assert(signInPerson.token == personDetails.username)
        assert(signInPerson.roles.size == 1)
        assert(signInPerson.roles[0] == Role.ROLE_USER.name)
    }
}

object SingInUseCaseTestSetup {
    private val jwtUtilMock = mock(JwtUtil::class.java)
    val mockPersonRepository = MockPersonRepository()
    val mockAuthenticationManager: AuthenticationManager = mock(AuthenticationManager::class.java)
    val mockAuthentication: Authentication = mock(Authentication::class.java)

    fun signInUseCase(): SignInUseCase {
        return SignInUseCaseImpl(
            authenticationManager = mockAuthenticationManager,
            jwtUtil = jwtUtilMock,
            personRepository = mockPersonRepository
        )
    }
}
