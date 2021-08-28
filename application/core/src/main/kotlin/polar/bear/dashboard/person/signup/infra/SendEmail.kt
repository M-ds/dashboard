package polar.bear.dashboard.person.signup.infra

import java.util.UUID

interface SendEmail {

    fun sendEmail(request: Request)
    data class Request(val username: String, val email: String, val token: UUID, val verifyUrl: String)
}