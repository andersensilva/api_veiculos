package com.wswork.api_veiculos.controller

import com.wswork.api_veiculos.entity.Marca
import com.wswork.api_veiculos.service.MarcaService
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/marca")
@Tag(name = "Marcas", description = "CRUD de marcas e operações relacionadas")
class MarcaController(
        private val marcaService: MarcaService
) {

    @GetMapping
    fun findAll(): ResponseEntity<List<Marca>> =
            ResponseEntity.ok(marcaService.findAll())

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Long): ResponseEntity<Marca> =
            marcaService.findById(id)
                    .map { ResponseEntity.ok(it) }
                    .orElse(ResponseEntity.notFound().build())

    @PostMapping
    fun create(@RequestBody marca: Marca): ResponseEntity<Marca> {
        val salva = marcaService.save(marca)
        return ResponseEntity.status(HttpStatus.CREATED).body(salva)
    }

    @PutMapping("/{id}")
    fun update(@PathVariable id: Long, @RequestBody marca: Marca): ResponseEntity<Marca> =
            marcaService.findById(id)
                    .map {
                        marca.id = id
                        ResponseEntity.ok(marcaService.save(marca))
                    }
                    .orElse(ResponseEntity.notFound().build())

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long): ResponseEntity<Void> {
        marcaService.delete(id)
        return ResponseEntity.noContent().build()
    }
}
