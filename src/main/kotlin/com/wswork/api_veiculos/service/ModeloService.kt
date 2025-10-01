package com.wswork.api_veiculos.service

import com.wswork.api_veiculos.entity.Modelo
import com.wswork.api_veiculos.repository.ModeloRepository

import org.springframework.stereotype.Service

@Service
class ModeloService(
        private val modeloRepository: ModeloRepository
) {

    fun findAll(): List<Modelo> = modeloRepository.findAll()

    fun findById(id: Long) = modeloRepository.findById(id)

    fun save(modelo: Modelo): Modelo = modeloRepository.save(modelo)

    fun delete(id: Long) = modeloRepository.deleteById(id)
}
