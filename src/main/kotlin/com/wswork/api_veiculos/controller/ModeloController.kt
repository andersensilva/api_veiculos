package com.wswork.api_veiculos.controller

import com.wswork.api_veiculos.entity.Modelo
import com.wswork.api_veiculos.service.ModeloService

import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/modelo")
@Tag(name = "Modelo", description = "CRUD de modelos e operações relacionadas")
class ModeloController(
        private val modeloService: ModeloService
) {

    @GetMapping
    fun findAll(): ResponseEntity<List<Modelo>> =
            ResponseEntity.ok(modeloService.findAll())

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Long): ResponseEntity<Modelo> =
            modeloService.findById(id)
                    .map { ResponseEntity.ok(it) }
                    .orElse(ResponseEntity.notFound().build())

    @PostMapping
    fun create(@RequestBody modelo: Modelo): ResponseEntity<Modelo> {
        val salva = modeloService.save(modelo)
        return ResponseEntity.status(HttpStatus.CREATED).body(salva)
    }

    @PutMapping("/{id}")
    fun update(@PathVariable id: Long, @RequestBody modelo: Modelo): ResponseEntity<Modelo> =
            modeloService.findById(id)
                    .map {
                        modelo.id = id
                        ResponseEntity.ok(modeloService.save(modelo))
                    }
                    .orElse(ResponseEntity.notFound().build())

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long): ResponseEntity<Void> {
        modeloService.delete(id)
        return ResponseEntity.noContent().build()
    }
}
