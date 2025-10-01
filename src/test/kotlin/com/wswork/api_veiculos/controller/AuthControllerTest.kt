package com.wswork.api_veiculos.controller

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@SpringBootTest
@AutoConfigureMockMvc
class AuthControllerTest {

    @Autowired
    lateinit var mockMvc: MockMvc

    @Test
    fun deveAutenticarUsuarioValido() {
        mockMvc.perform(
                post("/auth/login")
                        .param("username", "admin")
                        .param("password", "123456")
        ).andExpect(status().isOk)
    }

    @Test
    fun deveNegarUsuarioInvalido() {
        mockMvc.perform(
                post("/auth/login")
                        .param("username", "admin")
                        .param("password", "senhaErrada")
        ).andExpect(status().isUnauthorized)
    }
}
