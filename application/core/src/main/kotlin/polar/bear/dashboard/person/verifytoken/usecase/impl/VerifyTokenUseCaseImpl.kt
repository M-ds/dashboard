package polar.bear.dashboard.person.verifytoken.usecase.impl

import java.time.LocalDateTime
import java.util.UUID
import polar.bear.dashboard.person.infra.PersonRepository
import polar.bear.dashboard.person.signup.infra.SendEmail
import polar.bear.dashboard.person.verifytoken.domain.PersonRegisteredSuccess
import polar.bear.dashboard.person.verifytoken.domain.UpdateToken
import polar.bear.dashboard.person.verifytoken.infra.RetrieveTokenRepository
import polar.bear.dashboard.person.verifytoken.infra.UpdateTokenRepository
import polar.bear.dashboard.person.verifytoken.usecase.VerifyTokenUseCase
import polar.bear.dashboard.util.localdate.DateUtils

class VerifyTokenUseCaseImpl(
    val retrieveTokenRepository: RetrieveTokenRepository,
    val updateTokenRepository: UpdateTokenRepository,
    val personRepository: PersonRepository,
    val sendEmail: SendEmail
) : VerifyTokenUseCase {

    override fun verify(
        request: VerifyTokenUseCase.Request
    ): VerifyTokenUseCase.Response {
        val token = UUID.fromString(request.token)

        val registeredPerson = retrieveTokenRepository.retrieveToken(token)

        if (isExpired(registeredPerson.expirationDate)) {
            return VerifyTokenUseCase.Response(
                valid = false,
                tokenId = registeredPerson.tokenId
            )
        }

        val personRegistered = PersonRegisteredSuccess(
            personId = registeredPerson.personId
        )

        personRepository.successfulRegistered(personRegistered)

        return VerifyTokenUseCase.Response(
            valid = true
        )
    }

    override fun regenerate(
        regenerateRequest: VerifyTokenUseCase.RegenerateRequest
    ): VerifyTokenUseCase.RegenerateResponse {
        val tokenId = regenerateRequest.tokenId
        val newToken = UUID.randomUUID()
        val newExpirationDate = DateUtils.createExpirationDate(10)

        val updateToken = UpdateToken(
            tokenId = tokenId,
            newToken = newToken,
            newExpirationDate = newExpirationDate
        )

        val success = updateTokenRepository.updateToken(updateToken)

        if (success) {
            val personResponse = personRepository.getUsernameAndEmail(tokenId)

            val request = SendEmail.Request(
                username = personResponse.username,
                email = personResponse.email,
                token = newToken,
                verifyUrl = regenerateRequest.baseSiteUrl
            )
            sendEmail.sendEmail(request)
        }

        return VerifyTokenUseCase.RegenerateResponse(success)
    }

    private fun isExpired(expirationDate: LocalDateTime): Boolean {
        return DateUtils.currentTimeIsAfterTimeToCheck(expirationDate)
    }
}