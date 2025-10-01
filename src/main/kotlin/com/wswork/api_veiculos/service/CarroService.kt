package com.wswork.api_veiculos.service

import com.wswork.api_veiculos.entity.Carro
import com.wswork.api_veiculos.repository.CarroRepository
import org.springframework.stereotype.Service

@Service
class CarroService(
        private val carroRepository: CarroRepository
) {

    fun findAll(): List<Carro> = carroRepository.findAll()

    fun findById(id: Long) = carroRepository.findById(id)

    fun save(carro: Carro): Carro {
        carro.timestampCadastro = System.currentTimeMillis()
        return carroRepository.save(carro)
    }

    fun delete(id: Long) = carroRepository.deleteById(id)
}
