package polar.bear.dashboard.person.signup

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.anyString
import org.mockito.Mockito.mock
import org.springframework.security.crypto.password.PasswordEncoder
import polar.bear.dashboard.person.signup.infra.SendEmail
import polar.bear.dashboard.person.signup.usecase.SignupUseCase
import polar.bear.dashboard.person.signup.usecase.impl.SignupUseCaseImpl
import polar.bear.dashboard.repository.person.MockPersonRepository
import polar.bear.dashboard.repository.token.MockSaveTokenRepository
import polar.bear.dashboard.utils.Marker.GIVEN
import polar.bear.dashboard.utils.Marker.THEN
import polar.bear.dashboard.utils.Marker.WHEN

internal class SignupUseCaseTest {

    private val mockPersonRepository = SignupUseCaseTestSetup.mockPersonRepository
    private val mockSaveTokenRepository = SignupUseCaseTestSetup.mockSaveTokenRepository
    private val encodePassword = `when`(SignupUseCaseTestSetup.mockedPasswordEncoder.encode(anyString())).thenReturn("Encrypted")

    private val underTest = SignupUseCaseTestSetup.signupUseCase()

    @Test
    fun `Happy Flow of signing up`() {
        GIVEN
        val request = SignupUseCaseTestSetup.createRequest()

        mockPersonRepository.setEmailExists(false)
        mockPersonRepository.setUsernameExits(false)
        mockPersonRepository.setSuccessFulSave(true)
        mockSaveTokenRepository.setSaveToken(true)

        encodePassword

        WHEN
        val response = underTest.signup(request)

        THEN
        assertEquals(
            "Person is successfully registered. Please check your email address to confirm your registration.",
            response.signupResponse!!.message
        )
        assertTrue(response.valid)
        assertTrue(response.errorMessage.isEmpty())
    }

    @Test
    fun `Username already exists`() {
        GIVEN
        val request = SignupUseCaseTestSetup.createRequest()

        mockPersonRepository.setUsernameExits(true)

        WHEN
        val response = underTest.signup(request)

        THEN
        assertTrue(response.signupResponse == null)
        assertFalse(response.valid)
        assertEquals("Username already exists, please choose another.", response.errorMessage)
    }

    @Test
    fun `Email adress is already in use`() {
        GIVEN
        val request = SignupUseCaseTestSetup.createRequest()

        mockPersonRepository.setUsernameExits(false)
        mockPersonRepository.setEmailExists(true)

        WHEN
        val response = underTest.signup(request)

        THEN
        assertTrue(response.signupResponse == null)
        assertFalse(response.valid)
        assertEquals("Email address is already in use, please choose another.", response.errorMessage)
    }

    @Test
    fun `passwords are not equal`() {
        GIVEN
        val request = SignupUseCaseTestSetup.createRequest(
            password = "password-not-the-same",
            repeatedPassword = "password-not-similar"
        )

        WHEN
        val response = underTest.signup(request)

        THEN
        assertTrue(response.signupResponse == null)
        assertFalse(response.valid)
        assertEquals("Passwords are not equal!", response.errorMessage)
    }

    @Test
    fun `Username has invalid characters`() {
        GIVEN
        val request = SignupUseCaseTestSetup.createRequest(username = "username-123!")

        WHEN
        val response = underTest.signup(request)

        THEN
        assertTrue(response.signupResponse == null)
        assertFalse(response.valid)
        assertEquals("Username has to be filled in. And can only contain characters and numbers", response.errorMessage)
    }

    @Test
    fun `Username is empty and therefore invalid`() {
        GIVEN
        val request = SignupUseCaseTestSetup.createRequest(username = " ")

        WHEN
        val response = underTest.signup(request)

        THEN
        assertTrue(response.signupResponse == null)
        assertFalse(response.valid)
        assertEquals("Username has to be filled in. And can only contain characters and numbers", response.errorMessage)
    }

    @Test
    fun `Empty can not be empty and therefore invalid`() {
        GIVEN
        val request = SignupUseCaseTestSetup.createRequest(email = " ")

        WHEN
        val response = underTest.signup(request)

        THEN
        assertTrue(response.signupResponse == null)
        assertFalse(response.valid)
        assertEquals("Email address has to be filled in!", response.errorMessage)
    }

    @Test
    fun `Unsuccessfully save of token`() {
        GIVEN
        val request = SignupUseCaseTestSetup.createRequest()

        mockPersonRepository.setEmailExists(false)
        mockPersonRepository.setUsernameExits(false)
        mockPersonRepository.setSuccessFulSave(true)
        mockSaveTokenRepository.setSaveToken(false)

        encodePassword

        WHEN
        val response = underTest.signup(request)

        THEN
        assertTrue(response.signupResponse == null)
        assertFalse(response.valid)
        assertEquals(
            "Something went wrong while trying to create a user. Please contact the support, with your username and email address trying to create your account.",
            response.errorMessage
        )
    }

    @Test
    fun `Unsuccessfully save of person`() {
        GIVEN
        val request = SignupUseCaseTestSetup.createRequest()

        mockPersonRepository.setEmailExists(false)
        mockPersonRepository.setUsernameExits(false)
        mockPersonRepository.setSuccessFulSave(false)
        mockSaveTokenRepository.setSaveToken(true)

        encodePassword

        WHEN
        val response = underTest.signup(request)

        THEN
        assertTrue(response.signupResponse == null)
        assertFalse(response.valid)
        assertEquals(
            "Something went wrong while trying to create a user. Please contact the support, with your username and email address trying to create your account.",
            response.errorMessage
        )
    }
}

object SignupUseCaseTestSetup {

    val mockPersonRepository = MockPersonRepository()
    val mockSaveTokenRepository = MockSaveTokenRepository()
    val mockedPasswordEncoder: PasswordEncoder = mock(PasswordEncoder::class.java)

    fun signupUseCase(): SignupUseCase {
        return SignupUseCaseImpl(
            personRepository = mockPersonRepository,
            saveTokenRepository = mockSaveTokenRepository,
            passwordEncoder = mockedPasswordEncoder,
            sendEmail = mock(SendEmail::class.java)
        )
    }

    fun createRequest(
        username: String = "username",
        password: String = "password",
        repeatedPassword: String = "password",
        email: String = "email@mail.com",
        siteUrl: String = "localhost:8080"
    ): SignupUseCase.Request {
        return SignupUseCase.Request(
            username, email, password, repeatedPassword, siteUrl
        )
    }

}