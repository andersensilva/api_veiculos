package com.wswork.apiVeiculos.controller

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.jayway.jsonpath.JsonPath
import com.wswork.api_veiculos.ApiVeiculosApplication
import org.hamcrest.Matchers
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.MvcResult
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*

@SpringBootTest(classes = [ApiVeiculosApplication::class])
@AutoConfigureMockMvc(addFilters = false)
class MarcaControllerTest {

    @Autowired
    lateinit var mockMvc: MockMvc

    @Test
    fun deveCriarMarca() {
        val json = """{ "nomeMarca": "Honda" }"""

        mockMvc.perform(post("/api/marca")
                .contentType("application/json")
                .content(json))
                .andExpect(status().isCreated)
                .andExpect(jsonPath("$.nomeMarca").value("Honda"))
    }

    @Test
    fun deveListarMarcas() {
        // Criar uma marca antes de listar
        val json = """{ "nomeMarca": "Toyota" }"""
        mockMvc.perform(post("/api/marca")
                .contentType("application/json")
                .content(json))
                .andExpect(status().isCreated)

        mockMvc.perform(get("/api/marca"))
                .andExpect(status().isOk)
                .andExpect(jsonPath("$[*].nomeMarca", Matchers.hasItem("Toyota")))
    }

    @Test
    fun deveAtualizarMarca() {
        // Criar marca antes de atualizar
        val jsonCriar = """{ "nomeMarca": "Ford" }"""
        val result: MvcResult = mockMvc.perform(post("/api/marca")
                .contentType("application/json")
                .content(jsonCriar))
                .andExpect(status().isCreated)
                .andReturn()

        val content = result.response.contentAsString
        val mapper = jacksonObjectMapper()
        val node: JsonNode = mapper.readTree(content)
        val id: Long = node["id"].asLong()

        // Atualizar a marca criada
        val jsonAtualizar = """{ "nomeMarca": "Ford Atualizada" }"""
        mockMvc.perform(put("/api/marca/$id")
                .contentType("application/json")
                .content(jsonAtualizar))
                .andExpect(status().isOk)
                .andExpect(jsonPath("$.nomeMarca").value("Ford Atualizada"))
    }

    @Test
    fun deveExcluirMarca() {
        // Criar marca antes de excluir
        val jsonCriar = """{ "nomeMarca": "Chevrolet" }"""
        val result: MvcResult = mockMvc.perform(post("/api/marca")
                .contentType("application/json")
                .content(jsonCriar))
                .andExpect(status().isCreated)
                .andReturn()

        val content = result.response.contentAsString
        val mapper = jacksonObjectMapper()
        val node: JsonNode = mapper.readTree(content)
        val id: Long = node["id"].asLong()

        // Excluir a marca criada
        mockMvc.perform(delete("/api/marca/$id"))
                .andExpect(status().isNoContent)
    }
}
