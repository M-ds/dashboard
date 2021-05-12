package polar.bear.dashboard.security

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import polar.bear.dashboard.person.auth.MyUserDetailsService
import polar.bear.dashboard.util.text.TextUtils
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class JwtRequestFilter(
    private val jwtTokenProperties: JwtTokenProperties,
    private val myUserDetailsService: MyUserDetailsService
) : OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val jwtToken = parseJwt(request)
        if (jwtToken.isNullOrBlank()) {
            filterChain.doFilter(request, response)
            return
        }

        val tokenUsername = jwtTokenProperties.getUserNameFromToken(jwtToken)
        if (!tokenUsername.isNullOrBlank() && SecurityContextHolder.getContext().authentication == null) {

            val userDetails = myUserDetailsService.loadUserByUsername(tokenUsername)
            if (jwtTokenProperties.validateToken(jwtToken, userDetails.username)) {
                val usernamePasswordAuthenticationToken = UsernamePasswordAuthenticationToken(
                    userDetails,
                    null,
                    userDetails.authorities
                )

                usernamePasswordAuthenticationToken.details = WebAuthenticationDetailsSource().buildDetails(request)

                // After setting the Authentication in the context, we specify
                // that the current user is authenticated. So it passes the
                // Spring Security Configurations successfully.
                SecurityContextHolder.getContext().authentication = usernamePasswordAuthenticationToken
            }

            filterChain.doFilter(request, response)
        }
    }

    private fun parseJwt(
        request: HttpServletRequest
    ): String? {
        val headerAuth = request.getHeader("Authorization")
        if (TextUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")) {
            return headerAuth.substring(7, headerAuth.length)
        }
        return null
    }

}