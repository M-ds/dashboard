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

    private val redirectionURL = "http://localhost:1997"

    @GetMapping("/user/auth/_verify")
    fun verifyToken(
        @RequestParam(value = "code") token: String,
        httpServletRequest: HttpServletRequest
    ): ModelAndView {
        val baseUrl = httpServletRequest.localName
        println(baseUrl)
        val request = VerifyTokenUseCase.Request(
            token = token
        )
        val response = verifyTokenUseCase.verify(request)
        return if (response.valid) {
            ModelAndView("redirect:$redirectionURL/success.html")
        } else {
            ModelAndView("redirect:$redirectionURL/failure.html")
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

    @GetMapping("/user/auth/_welcome")
    fun welcomeScreen(): ModelAndView {
        return ModelAndView("welcome")
    }
}

object RedirectionNames {
    const val SUCCESS = "success"
    const val FAILURE = "failure"
}