package polar.bear.dashboard.person.signIn

import org.springframework.http.HttpHeaders
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import polar.bear.dashboard.common.reply.user.LoginReply
import polar.bear.dashboard.person.signIn.dto.SignInResponseDto
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
    ): ResponseEntity<LoginReply> {
        val request = SignInUseCase.SignInRequest(
            username = loginRequest.username,
            password = loginRequest.password
        )

        val response = signInUseCase.signIn(request)

        val signInResponseDto = SignInResponseDto.from(
            response.signInPerson
        )

        return ResponseEntity.ok()
            .header(
                HttpHeaders.AUTHORIZATION,
                response.signInPerson.token
            ).body(
                LoginReply(
                    valid = true,
                    error = null,
                    model = signInResponseDto
                )
            )
    }
}