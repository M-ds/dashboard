package polar.bear.dashboard.person.verify

import javax.servlet.http.HttpServletRequest
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import polar.bear.dashboard.person.verifytoken.usecase.VerifyTokenUseCase
import org.springframework.web.servlet.ModelAndView
import polar.bear.dashboard.person.verify.RedirectionNames.FAILURE
import polar.bear.dashboard.person.verify.RedirectionNames.SUCCESS

@RestController
class VerifyRegistrationTokenController(
    private val verifyTokenUseCase: VerifyTokenUseCase
) {

    @GetMapping("/user/auth/_verify")
    fun verifyToken(
        @RequestParam(value = "code") token: String,
        httpServletRequest: HttpServletRequest
    ): ModelAndView {
        val request = VerifyTokenUseCase.Request(
            token = token
        )
        val response = verifyTokenUseCase.verify(request)
        return if (response.valid) {
            ModelAndView("redirect:_$SUCCESS")
        } else {
            ModelAndView("redirect:_$FAILURE")
        }
    }

    @GetMapping("/user/auth/_success")
    fun successScreen(): ModelAndView {
        return ModelAndView(SUCCESS)
    }

    @GetMapping("/user/auth/_failure")
    fun failureScreen(): ModelAndView {
        return ModelAndView(FAILURE)
    }
}

object RedirectionNames {
    const val SUCCESS = "success"
    const val FAILURE = "failure"
}