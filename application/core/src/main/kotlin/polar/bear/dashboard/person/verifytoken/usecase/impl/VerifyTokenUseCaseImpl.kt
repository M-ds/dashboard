package polar.bear.dashboard.person.verifytoken.usecase.impl

import java.time.LocalDateTime
import java.util.UUID
import polar.bear.dashboard.person.infra.PersonRepository
import polar.bear.dashboard.person.verifytoken.domain.PersonRegisteredSuccess
import polar.bear.dashboard.person.verifytoken.infra.RetrieveTokenRepository
import polar.bear.dashboard.person.verifytoken.usecase.VerifyTokenUseCase

class VerifyTokenUseCaseImpl(
    val retrieveTokenRepository: RetrieveTokenRepository,
    val personRepository: PersonRepository
) : VerifyTokenUseCase {

    override fun verify(
        request: VerifyTokenUseCase.Request
    ): VerifyTokenUseCase.Response {
        val token = UUID.fromString(request.token)

        val registeredPerson = retrieveTokenRepository.retrieveToken(token)

        if (isExpired(registeredPerson.expirationDate)) {
            //TODO("Everything should be updated!")
            return VerifyTokenUseCase.Response(
                valid = false,
                message = "Registration token is expired! Please request a new one!"
            )
        }

        val personRegistered = PersonRegisteredSuccess(
            personId = registeredPerson.personId
        )

        personRepository.successfulRegistered(personRegistered)

        return VerifyTokenUseCase.Response(
            valid = true,
            message = "Successfully Registered! Please login now!"
        )
    }

    private fun isExpired(expirationDate: LocalDateTime): Boolean {
        return LocalDateTime.now().isAfter(expirationDate)
    }
}