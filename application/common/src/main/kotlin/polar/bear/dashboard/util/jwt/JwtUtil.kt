package polar.bear.dashboard.util.jwt

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import java.util.Date

class JwtUtil(
    private val jwtSecret: String,
    private val jwtTokenExpirationTime: Int
) {

    fun generateToken(
        username: String
    ): String {
        return Jwts
            .builder()
            .setClaims(mutableMapOf())
            .setSubject(username)
            .setIssuedAt(Date(System.currentTimeMillis()))
            .setExpiration(Date(System.currentTimeMillis() + jwtTokenExpirationTime * 1000))
            .signWith(SignatureAlgorithm.HS512, jwtSecret)
            .compact()
    }
}