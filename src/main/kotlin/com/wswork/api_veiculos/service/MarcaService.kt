package com.wswork.api_veiculos.service

import com.wswork.api_veiculos.entity.Marca
import com.wswork.api_veiculos.repository.MarcaRepository

import org.springframework.stereotype.Service

@Service
class MarcaService(
        private val marcaRepository: MarcaRepository
) {

    fun findAll(): List<Marca> = marcaRepository.findAll()

    fun findById(id: Long) = marcaRepository.findById(id)

    fun save(marca: Marca): Marca = marcaRepository.save(marca)

    fun delete(id: Long) = marcaRepository.deleteById(id)
}
