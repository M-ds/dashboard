package polar.bear.dashboard.person.verify

import javax.servlet.http.HttpServletRequest
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.ModelAndView
import polar.bear.dashboard.person.domain.TokenId
import polar.bear.dashboard.person.verify.RedirectionNames.FAILURE
import polar.bear.dashboard.person.verify.RedirectionNames.SUCCESS
import polar.bear.dashboard.person.verifytoken.usecase.VerifyTokenUseCase

@RestController
class VerifyRegistrationTokenController(
    private val verifyTokenUseCase: VerifyTokenUseCase
) {

    @GetMapping("/user/auth/_verify")
    fun verifyToken(
        @RequestParam(value = "code") token: String
    ): ModelAndView {
        val request = VerifyTokenUseCase.Request(
            token = token
        )
        val response = verifyTokenUseCase.verify(request)
        return if (response.valid) {
            ModelAndView("redirect:_$SUCCESS")
        } else {
            ModelAndView("redirect:_$FAILURE?tokenId=${response.tokenId!!}")
        }
    }

    @GetMapping("/user/auth/_regenerate")
    fun regenerateToken(
        @RequestParam(value = "tokenId") tokenId: String,
        httpServletRequest: HttpServletRequest,
    ) {
        val request = VerifyTokenUseCase.RegenerateRequest(
            tokenId = TokenId.fromString(tokenId),
            baseSiteUrl = getSiteUrl(httpServletRequest)
        )
        val response = verifyTokenUseCase.regenerate(request)
    }

    @GetMapping("/user/auth/_success")
    fun successScreen(): ModelAndView {
        return ModelAndView(SUCCESS)
    }

    @GetMapping("/user/auth/_failure")
    fun failureScreen(
        @RequestParam("tokenId") tokenId: String
    ): ModelAndView {
        return ModelAndView(FAILURE, mapOf<String, Any>("tokenId" to tokenId))
    }

    private fun getSiteUrl(httpServletRequest: HttpServletRequest): String {
        val siteUrl = httpServletRequest.requestURL.toString()
        return siteUrl.replace(httpServletRequest.servletPath, "")
    }
}

object RedirectionNames {
    const val SUCCESS = "success"
    const val FAILURE = "failure"
}