package polar.bear.dashboard.person.signIn

import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import polar.bear.dashboard.common.reply.JsonError
import polar.bear.dashboard.person.signIn.dto.SignInResponseDto
import polar.bear.dashboard.person.signIn.reply.JsonLoginReply
import polar.bear.dashboard.person.signIn.request.LoginRequest
import polar.bear.dashboard.person.usecase.SignInUseCase

@CrossOrigin(origins = ["http://localhost:1994"])
@RestController
class PersonSignInController(
    private val signInUseCase: SignInUseCase
) {

    @PostMapping("/user/auth/_log-in")
    fun login(
        @RequestBody loginRequest: LoginRequest
    ): JsonLoginReply {
        val request = SignInUseCase.SignInRequest(
            username = loginRequest.username,
            password = loginRequest.password
        )

        try {
            val response = signInUseCase.signIn(request)

            if (!response.valid) {
                return JsonLoginReply(
                    valid = response.valid,
                    error = JsonError(response.errorMessage),
                    model = null
                )
            }

            return JsonLoginReply(
                valid = true,
                error = null,
                model = SignInResponseDto.from(
                    response.signInPerson!!
                )
            )

        } catch (ex: Exception) {
            val errorMessage = if (ex.message!!.contains("Bad credentials")) {
                "Invalid password, please try again."
            } else {
                ex.message!!
            }

            return JsonLoginReply(
                valid = false,
                error = JsonError(errorMessage = errorMessage),
                model = null
            )
        }
    }
}