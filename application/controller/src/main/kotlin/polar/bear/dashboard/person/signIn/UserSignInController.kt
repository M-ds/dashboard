package polar.bear.dashboard.person.signIn

import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
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
class UserSignInController(
    private val signInUseCase: SignInUseCase
) {

    @PostMapping("/user/auth/_log-in")
    fun login(
        @RequestBody loginRequest: LoginRequest
    ): ResponseEntity<JsonLoginReply> {
        val request = SignInUseCase.SignInRequest(
            username = loginRequest.username,
            password = loginRequest.password
        )

        val response = signInUseCase.signIn(request)

        if (!response.valid) {
            return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(
                    JsonLoginReply(
                        valid = response.valid,
                        error = JsonError(response.errorMessage),
                        model = null
                    )
                )
        }

        return ResponseEntity.ok()
            .header(
                HttpHeaders.AUTHORIZATION,
                response.signInPerson!!.token
            ).body(
                JsonLoginReply(
                    valid = true,
                    error = null,
                    model = SignInResponseDto.from(
                        response.signInPerson!!
                    )
                )
            )
    }
}