package com.wswork.api_veiculos.security

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
import org.springframework.stereotype.Component
import java.security.Key
import java.util.Date

@Component
class JwtUtil {

    companion object {
        private const val EXPIRATION_TIME: Long = 1000 * 60 * 60 // 1 hora
        private val SECRET_KEY: Key = Keys.secretKeyFor(SignatureAlgorithm.HS256)
    }

    fun generateToken(username: String): String {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(Date())
                .setExpiration(Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SECRET_KEY)
                .compact()
    }

    fun extractUsername(token: String): String {
        return getClaims(token).subject
    }

    fun validateToken(token: String): Boolean {
        return try {
            !getClaims(token).expiration.before(Date())
        } catch (e: Exception) {
            false
        }
    }

    private fun getClaims(token: String): Claims {
        return Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY)
                .build()
                .parseClaimsJws(token)
                .body
    }
}
