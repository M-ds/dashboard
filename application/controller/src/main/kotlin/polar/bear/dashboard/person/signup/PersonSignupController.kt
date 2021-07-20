package polar.bear.dashboard.person.signup

import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import polar.bear.dashboard.common.reply.JsonError
import polar.bear.dashboard.person.signup.dto.SignupResponseDto
import polar.bear.dashboard.person.signup.reply.JsonSignupReply
import polar.bear.dashboard.person.signup.request.SignupRequest
import polar.bear.dashboard.person.usecase.SignupUseCase

@CrossOrigin(origins = ["http://localhost:1994"])
@RestController
class PersonSignupController(
    private val signupUseCase: SignupUseCase
) {

    @PostMapping("/user/auth/_signup")
    fun signup(
        @RequestBody signupRequest: SignupRequest
    ): JsonSignupReply {
        val request = SignupUseCase.Request(
            username = signupRequest.username,
            email = signupRequest.email,
            password = signupRequest.password,
            repeatedPassword = signupRequest.passwordConformation
        )

        val result = signupUseCase.signup(request)

        return if (result.valid) {
            JsonSignupReply(
                valid = result.valid,
                error = null,
                model = SignupResponseDto.fromDomain(
                    result.signupResponse!!
                )
            )
        } else {
            JsonSignupReply(
                valid = result.valid,
                error = JsonError(
                    result.errorMessage
                ),
                model = null
            )
        }
    }
}