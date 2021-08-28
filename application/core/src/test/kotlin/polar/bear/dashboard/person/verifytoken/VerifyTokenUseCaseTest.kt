package polar.bear.dashboard.person.verifytoken

import java.time.LocalDateTime
import java.time.Month
import java.util.UUID
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import polar.bear.dashboard.person.domain.TokenId
import polar.bear.dashboard.person.infra.PersonRepository
import polar.bear.dashboard.person.signup.infra.SendEmail
import polar.bear.dashboard.person.verifytoken.domain.RegisteredPerson
import polar.bear.dashboard.person.verifytoken.usecase.VerifyTokenUseCase
import polar.bear.dashboard.person.verifytoken.usecase.impl.VerifyTokenUseCaseImpl
import polar.bear.dashboard.repository.person.MockPersonRepository
import polar.bear.dashboard.repository.token.MockRetrieveTokenRepository
import polar.bear.dashboard.repository.token.MockUpdateTokenRepository
import polar.bear.dashboard.utils.Marker.GIVEN
import polar.bear.dashboard.utils.Marker.THEN
import polar.bear.dashboard.utils.Marker.WHEN

class VerifyTokenUseCaseTest {

    private val mockPersonRepository = VerifyTokenUseCaseTestSetup.mockPersonRepository
    private val updateTokenRepository = VerifyTokenUseCaseTestSetup.mockUpdateTokenRepository
    private val retrieveTokenRepository = VerifyTokenUseCaseTestSetup.mockRetrieveTokenRepository

    private val underTest = VerifyTokenUseCaseTestSetup.verifyTokenUseCase()

    private val tokenId = UUID.fromString("4ba8ad51-12d5-4a92-bc60-5409c40c2f7d")
    private val personId = UUID.fromString("b0990b66-247c-4bae-96af-11057437778c")
    private val expiredDate = LocalDateTime.of(2000, Month.JULY, 3, 5, 5)
    private val validDate = LocalDateTime.now().plusMinutes(2)

    @Test
    fun `Happy flow to verify a token`() {
        GIVEN
        val request = VerifyTokenUseCase.Request(token = tokenId.toString())

        val registeredPerson = RegisteredPerson(
            personId = personId,
            active = false,
            tokenId = tokenId,
            expirationDate = validDate
        )

        retrieveTokenRepository.setRegsiteredPerson(registeredPerson)
        mockPersonRepository.setSuccessFulSave(true)

        WHEN
        val response = underTest.verify(request)

        THEN
        assertTrue(response.valid)
        assertNull(response.tokenId)
    }

    @Test
    fun `Token has expired`() {
        GIVEN
        val request = VerifyTokenUseCase.Request(token = tokenId.toString())

        val registeredPerson = RegisteredPerson(
            personId = personId,
            active = false,
            tokenId = tokenId,
            expirationDate = expiredDate
        )

        retrieveTokenRepository.setRegsiteredPerson(registeredPerson)

        WHEN
        val response = underTest.verify(request)

        THEN
        assertFalse(response.valid)
        assert(tokenId == response.tokenId)
    }

    private val usernameAndEmailResponse =
        PersonRepository.UsernameAndEmailResponse(username = "username", email = "mail@mail.com")

    @Test
    fun `Happy flow to regenerate token`() {
        GIVEN
        val request = VerifyTokenUseCase.RegenerateRequest(tokenId = TokenId(tokenId), baseSiteUrl = "localhost:8080")
        updateTokenRepository.setUpdated(true)
        mockPersonRepository.setUsernameEmailResponse(usernameAndEmailResponse)

        WHEN
        val response = underTest.regenerate(request)

        THEN
        verify(VerifyTokenUseCaseTestSetup.mockSendEmail, times(1)).sendEmail(any(SendEmail.Request::class.java))
        assertTrue(response.success)
    }

    // Necessary to work with the any type of mockito
    // https://stackoverflow.com/questions/59230041/argumentmatchers-any-must-not-be-null
    private fun <T> any(type: Class<T>): T = Mockito.any(type)

    @Test
    fun `Could not update a regeneration token`() {
        GIVEN
        val request = VerifyTokenUseCase.RegenerateRequest(tokenId = TokenId(tokenId), baseSiteUrl = "localhost:8080")
        updateTokenRepository.setUpdated(false)

        WHEN
        val response = underTest.regenerate(request)

        THEN
        verify(VerifyTokenUseCaseTestSetup.mockSendEmail, times(0)).sendEmail(any(SendEmail.Request::class.java))
        assertFalse(response.success)
    }
}

object VerifyTokenUseCaseTestSetup {

    val mockPersonRepository = MockPersonRepository()
    val mockRetrieveTokenRepository = MockRetrieveTokenRepository()
    val mockUpdateTokenRepository = MockUpdateTokenRepository()
    val mockSendEmail: SendEmail = mock(SendEmail::class.java)

    fun verifyTokenUseCase(): VerifyTokenUseCase {
        return VerifyTokenUseCaseImpl(
            retrieveTokenRepository = mockRetrieveTokenRepository,
            personRepository = mockPersonRepository,
            updateTokenRepository = mockUpdateTokenRepository,
            sendEmail = mockSendEmail
        )
    }
}