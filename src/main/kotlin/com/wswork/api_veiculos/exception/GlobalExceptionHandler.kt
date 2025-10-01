package com.wswork.api_veiculos.exception

import io.jsonwebtoken.ExpiredJwtException
import io.jsonwebtoken.MalformedJwtException
import jakarta.servlet.http.HttpServletRequest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.AuthenticationException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import java.security.SignatureException

@ControllerAdvice
class GlobalExceptionHandler {

    // Tratamento de falha de login / autenticação
    @ExceptionHandler(AuthenticationException::class)
    fun handleAuthException(ex: AuthenticationException): ResponseEntity<Map<String, String>> =
            ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(mapOf("error" to "Credenciais inválidas"))

    @ExceptionHandler(ExpiredJwtException::class)
    fun handleExpiredJwt(ex: ExpiredJwtException, request: HttpServletRequest): ResponseEntity<Map<String, Any>> {
        val body = mapOf(
                "error" to "Token expirado",
                "message" to "Seu token expirou, faça login novamente.",
                "path" to request.servletPath
        )
        return ResponseEntity(body, HttpStatus.UNAUTHORIZED)
    }

    @ExceptionHandler(SignatureException::class, MalformedJwtException::class)
    fun handleInvalidJwt(ex: Exception, request: HttpServletRequest): ResponseEntity<Map<String, Any>> {
        val body = mapOf(
                "error" to "Token inválido",
                "message" to "O token fornecido é inválido ou mal formado.",
                "path" to request.servletPath
        )
        return ResponseEntity(body, HttpStatus.UNAUTHORIZED)
    }

    // Tratamento genérico para RuntimeException
    @ExceptionHandler(RuntimeException::class)
    fun handleRuntimeException(ex: RuntimeException): ResponseEntity<Map<String, String>> =
            ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(mapOf("error" to (ex.message ?: "Erro inesperado")))
}
