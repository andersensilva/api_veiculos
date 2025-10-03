package com.wswork.api_veiculos.service

import com.wswork.api_veiculos.entity.Carro
import com.wswork.api_veiculos.repository.CarroRepository
import com.wswork.api_veiculos.repository.ModeloRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class CarroService(
        private val carroRepository: CarroRepository,
        private val modeloRepository: ModeloRepository
) {

    fun findAll(): List<Carro> = carroRepository.findAll()

    fun findById(id: Long): Optional<Carro> = carroRepository.findById(id)

    fun save(carro: Carro): Carro {
        val modeloExistente = modeloRepository.findById(carro.modelo?.id
                ?: throw RuntimeException("Modelo não informado"))
                .orElseThrow { RuntimeException("Modelo não encontrada") }

        carro.modelo = modeloExistente
        carro.timestamp_cadastro = System.currentTimeMillis()
        return carroRepository.save(carro)
    }

    fun delete(id: Long) {
        carroRepository.deleteById(id)
    }
}
