package com.wswork.api_veiculos.controller

import com.wswork.api_veiculos.entity.Modelo
import com.wswork.api_veiculos.repository.CarroRepository
import com.wswork.api_veiculos.repository.ModeloRepository

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
class ModeloControllerTest {

    @Autowired
    lateinit var mockMvc: MockMvc

    @Autowired
    lateinit var modeloRepository: ModeloRepository

    @Autowired
    lateinit var carroRepository: CarroRepository

    @BeforeEach
    fun setup() {
        // Limpa carros e modelos antes de cada teste
        carroRepository.deleteAll()
        modeloRepository.deleteAll()
    }

    @Test
    fun deveCriarModelo() {
        val json = """{ "nome": "Civic", "marcaId": 1 }"""

        mockMvc.perform(post("/api/modelo")
                .contentType("application/json")
                .content(json))
                .andExpect(status().isCreated)
                .andExpect(jsonPath("$.nome").value("Civic"))
    }

    @Test
    fun deveListarModelos() {
        mockMvc.perform(get("/api/modelo"))
                .andExpect(status().isOk)
    }

    @Test
    fun deveAtualizarModelo() {
        // Primeiro cria o modelo
        val modelo = Modelo(nome = "Civic")
        modeloRepository.save(modelo)

        val json = """{ "nome": "Civic Touring", "marcaId": 1 }"""

        mockMvc.perform(put("/api/modelo/${modelo.id}")
                .contentType("application/json")
                .content(json))
                .andExpect(status().isOk)
                .andExpect(jsonPath("$.nome").value("Civic Touring"))
    }

    @Test
    fun deveExcluirModelo() {
        // Cria um modelo sem carros vinculados
        val modelo = Modelo(nome = "Gol")
        modeloRepository.save(modelo)

        mockMvc.perform(delete("/api/modelo/${modelo.id}"))
                .andExpect(status().isNoContent)
    }
}
