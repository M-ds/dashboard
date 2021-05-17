package polar.bear.dashboard.security

import io.jsonwebtoken.Jwts
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component
import polar.bear.dashboard.util.jwt.JwtUtil
import java.util.Date

@Component
class JwtTokenProperties {

    @Value("\${dashboard.jwt.secret}")
    private val jwtSecret: String = ""

    @Value("\${dashboard.jwt.expiration}")
    private val jwtTokenExpirationTime: Int = 0

    @Bean
    fun initJwtUtil(): JwtUtil {
        return JwtUtil(
            jwtSecret = jwtSecret,
            jwtTokenExpirationTime = jwtTokenExpirationTime
        )
    }

    fun getUserNameFromToken(
        token: String
    ): String? {
        return Jwts
            .parser()
            .setSigningKey(jwtSecret)
            .parseClaimsJws(token)
            .body
            .subject
    }

    fun validateToken(
        token: String,
        username: String
    ): Boolean {
        val foundUsername = getUserNameFromToken(token)
        return (foundUsername == username && !isTokenExpired(token))
    }

    private fun isTokenExpired(
        token: String
    ): Boolean {
        val expiration = getExpirationDateFromToken(token)
        return expiration.before(Date())
    }

    private fun getExpirationDateFromToken(
        token: String
    ): Date {
        return Jwts
            .parser()
            .setSigningKey(jwtSecret)
            .parseClaimsJws(token)
            .body
            .expiration
    }
}