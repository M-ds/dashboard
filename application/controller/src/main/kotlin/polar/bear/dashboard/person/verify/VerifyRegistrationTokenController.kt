package polar.bear.dashboard.person.verify

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import polar.bear.dashboard.person.verifytoken.usecase.VerifyTokenUseCase

@RestController
class VerifyRegistrationTokenController(
    private val verifyTokenUseCase: VerifyTokenUseCase
) {

    @GetMapping("/user/auth/_verify")
    fun verifyToken(
        @RequestParam(value = "code") token: String
    ) {
        val request = VerifyTokenUseCase.Request(
            token = token
        )
        val response = verifyTokenUseCase.verify(request)
    }
}