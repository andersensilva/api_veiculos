package com.wswork.api_veiculos.controller

import com.wswork.api_veiculos.entity.Carro
import com.wswork.api_veiculos.repository.CarroRepository
import com.wswork.api_veiculos.service.CarroService
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.beans.factory.annotation.Autowired

@RestController
@RequestMapping("/api/carros")
@Tag(name = "Carros", description = "CRUD de carros e operações relacionadas")
class CarroController @Autowired constructor(
        private val carroRepository: CarroRepository,
        private val carroService: CarroService
) {

    @GetMapping
    fun findAll(): ResponseEntity<List<Carro>> =
            ResponseEntity.ok(carroService.findAll())

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Long): ResponseEntity<Carro> =
            carroService.findById(id)
                    .map { ResponseEntity.ok(it) }
                    .orElse(ResponseEntity.notFound().build())

    @PostMapping
    fun create(@RequestBody carro: Carro): ResponseEntity<Carro> {
        val carroSalvo = carroService.save(carro)
        return ResponseEntity.status(HttpStatus.CREATED).body(carroSalvo)
    }

    @PutMapping("/{id}")
    fun update(@PathVariable id: Long, @RequestBody carro: Carro): ResponseEntity<Carro> =
            carroService.findById(id).map {
                carro.id = id
                ResponseEntity.ok(carroService.save(carro))
            }.orElse(ResponseEntity.notFound().build())

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long): ResponseEntity<Void> {
        carroService.delete(id)
        return ResponseEntity.noContent().build()
    }
}
