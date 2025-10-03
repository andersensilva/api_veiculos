package com.wswork.api_veiculos.controller

import com.wswork.api_veiculos.dto.CarroDTO
import com.wswork.api_veiculos.entity.Carro
import com.wswork.api_veiculos.repository.CarroRepository
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/carros-detalhes")
@Tag(name = "Carros Detalhados", description = "Traz Descrição, Marca, e Modelo dos veículos")
class CarroConsultaController(
        private val carroRepository: CarroRepository
) {

    @GetMapping
    fun listarCarros(): Map<String, Any> {
        val carros: List<Carro> = carroRepository.findAll()
        val carrosDTO: List<CarroDTO> = carros.map { CarroDTO(it) }

        return mapOf("cars" to carrosDTO)
    }
}
