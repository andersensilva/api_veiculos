package com.wswork.api_veiculos.controller

import com.wswork.api_veiculos.security.JwtUtil
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.AuthenticationException
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/auth")
@Tag(name = "Login", description = "Geração do Token de autenticação")
class AuthController(
        private val authManager: AuthenticationManager,
        private val jwtUtil: JwtUtil
) {

    @PostMapping("/login")
    fun login(
            @RequestParam username: String,
            @RequestParam password: String
    ): Map<String, String> {
        try {
            authManager.authenticate(UsernamePasswordAuthenticationToken(username, password))
            val token = jwtUtil.generateToken(username)
            return mapOf("token" to token)
        } catch (e: AuthenticationException) {
            throw e
        }
    }
}
