package com.wswork.api_veiculos.controller

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.jdbc.Sql
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false) // ignora filtros de segurança
class CarroControllerTest {

    @Autowired
    lateinit var mockMvc: MockMvc

    // --------------------------
    // CREATE
    // --------------------------
    @Test
    @Sql(statements = [
        "INSERT INTO marca (nome_marca) VALUES ('HONDA')",
        "INSERT INTO modelo (nome, valor_fipe, marca_id) VALUES ('CIVIC', 120000, 1)"
    ])
    fun deveCriarCarro() {
        val json = """
            {
                "ano": 2022,
                "combustivel": "FLEX",
                "numPortas": 4,
                "cor": "PRATA",
                "modeloId": 1,
                "timestampCadastro": 1696539488
            }
        """

        mockMvc.perform(post("/api/carros")
                .contentType("application/json")
                .content(json))
                .andExpect(status().isCreated)
                .andExpect(jsonPath("$.cor").value("PRATA"))
    }

    // --------------------------
    // READ
    // --------------------------
    @Test
    @Sql(statements = [
        "INSERT INTO marca (nome_marca) VALUES ('HONDA')",
        "INSERT INTO modelo (nome, valor_fipe, marca_id) VALUES ('CIVIC', 120000, 1)",
        "INSERT INTO carro (timestamp_cadastro, ano, combustivel, num_portas, cor, modelo_id) " +
                "VALUES (1696539488, 2022, 'FLEX', 4, 'PRATA', 1)"
    ])
    fun deveListarCarros() {
        mockMvc.perform(get("/api/carros"))
                .andExpect(status().isOk)
                .andExpect(jsonPath("$[?(@.cor == 'PRATA')]").exists())
    }

    // --------------------------
    // UPDATE
    // --------------------------
    @Test
    @Sql(statements = [
        "INSERT INTO marca (nome_marca) VALUES ('HONDA')",
        "INSERT INTO modelo (nome, valor_fipe, marca_id) VALUES ('CIVIC', 120000, 1)",
        "INSERT INTO carro (timestamp_cadastro, ano, combustivel, num_portas, cor, modelo_id) " +
                "VALUES (1696539488, 2022, 'FLEX', 4, 'PRATA', 1)"
    ])
    fun deveAtualizarCarro() {
        val json = """
            {
                "ano": 2023,
                "combustivel": "GASOLINA",
                "numPortas": 4,
                "cor": "AZUL",
                "modeloId": 1,
                "timestampCadastro": 1696539488
            }
        """

        mockMvc.perform(put("/api/carros/1")
                .contentType("application/json")
                .content(json))
                .andExpect(status().isOk)
                .andExpect(jsonPath("$.cor").value("AZUL"))
    }

    // --------------------------
    // DELETE
    // --------------------------
    @Test
    @Sql(statements = [
        "INSERT INTO marca (nome_marca) VALUES ('HONDA')",
        "INSERT INTO modelo (nome, valor_fipe, marca_id) VALUES ('CIVIC', 120000, 1)",
        "INSERT INTO carro (timestamp_cadastro, ano, combustivel, num_portas, cor, modelo_id) " +
                "VALUES (1696539488, 2022, 'FLEX', 4, 'PRATA', 1)"
    ])
    fun deveExcluirCarro() {
        mockMvc.perform(delete("/api/carros/1"))
                .andExpect(status().isNoContent)
    }
}
