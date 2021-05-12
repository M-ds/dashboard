package polar.bear.dashboard.person.signIn

import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
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
    ) {
        val request = SignInUseCase.SignInRequest(
            username = loginRequest.username,
            password = loginRequest.password
        )

        val response = signInUseCase.signIn(request)



    }
}