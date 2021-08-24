package polar.bear.dashboard.person.verify

import javax.servlet.http.HttpServletRequest
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.ModelAndView
import polar.bear.dashboard.person.domain.TokenId
import polar.bear.dashboard.person.verify.RedirectionNames.FAILURE
import polar.bear.dashboard.person.verify.RedirectionNames.REGENERATED_TOKEN_SCREEN
import polar.bear.dashboard.person.verify.RedirectionNames.SUCCESS
import polar.bear.dashboard.person.verifytoken.usecase.VerifyTokenUseCase

/**
 * This class is responsible for all the Token regenerations and so on.
 * These redirects are purely based on Thymeleaf and are not implemented with Vue.
 *
 * I had no real idea how to do this properly. Therefore I've decided to do it with thymeleaf.
 * Perhaps in the future we should look back at this. And improve upon it!
 *
 * Sorry not sorry future me!
 */
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
    ): ModelAndView {
        val request = VerifyTokenUseCase.RegenerateRequest(
            tokenId = TokenId.fromString(tokenId),
            baseSiteUrl = getSiteUrl(httpServletRequest)
        )
        verifyTokenUseCase.regenerate(request)
        return ModelAndView("redirect:_$REGENERATED_TOKEN_SCREEN")
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

    @GetMapping("/user/auth/_regenerated_token")
    fun regeneratedToken(): ModelAndView {
        return ModelAndView(REGENERATED_TOKEN_SCREEN)
    }

    private fun getSiteUrl(httpServletRequest: HttpServletRequest): String {
        val siteUrl = httpServletRequest.requestURL.toString()
        return siteUrl.replace(httpServletRequest.servletPath, "")
    }
}

object RedirectionNames {
    const val SUCCESS = "success"
    const val FAILURE = "failure"
    const val REGENERATED_TOKEN_SCREEN = "regenerated_token"
}