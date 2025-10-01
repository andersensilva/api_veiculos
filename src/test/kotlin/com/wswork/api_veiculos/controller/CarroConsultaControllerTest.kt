package com.wswork.api_veiculos.controller

import com.wswork.api_veiculos.entity.Carro
import com.wswork.api_veiculos.repository.CarroRepository
import org.hamcrest.Matchers.hasSize
import org.hamcrest.Matchers.`is`
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
class CarroConsultaControllerTest {

    @Autowired
    lateinit var mockMvc: MockMvc

    @Autowired
    lateinit var carroRepository: CarroRepository

    @BeforeEach
    fun setup() {
        // Limpa a tabela antes de cada teste
        carroRepository.deleteAll()
    }

    @Test
    fun `deve listar carros com detalhes`() {
        // Inserir carros no banco
        val carro1 = Carro(cor = "PRATA", ano = 2022, combustivel = "FLEX", numPortas = 4)
        val carro2 = Carro(cor = "AZUL", ano = 2021, combustivel = "GASOLINA", numPortas = 2)
        carroRepository.saveAll(listOf(carro1, carro2))

        mockMvc.perform(get("/carros-detalhes")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk)
                .andExpect(jsonPath("$.cars", hasSize<Any>(2)))
                .andExpect(jsonPath("$.cars[0].cor", `is`("PRATA")))
                .andExpect(jsonPath("$.cars[1].cor", `is`("AZUL")))
    }
}
