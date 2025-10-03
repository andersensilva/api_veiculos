package com.wswork.api_veiculos.service

import com.wswork.api_veiculos.entity.Marca
import com.wswork.api_veiculos.entity.Modelo
import com.wswork.api_veiculos.repository.CarroRepository
import com.wswork.api_veiculos.repository.MarcaRepository
import com.wswork.api_veiculos.repository.ModeloRepository
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.stereotype.Service

@Service
class ModeloService(
        private val modeloRepository: ModeloRepository,
        private val marcaRepository: MarcaRepository,
        private val carroRepository: CarroRepository
) {

    fun findAll(): List<Modelo> = modeloRepository.findAll()

    fun findById(id: Long) = modeloRepository.findById(id)

    fun countCarrosVinculados(modeloId: Long): Long = carroRepository.countByModeloId(modeloId)

    fun save(modelo: Modelo): Modelo {
        val marcaExistente: Marca = modelo.marca?.id?.let { id ->
            marcaRepository.findById(id)
                    .orElseThrow { RuntimeException("Marca não encontrada") }
        } ?: throw RuntimeException("Marca não informada")
        modelo.marca = marcaExistente
        return modeloRepository.save(modelo)
    }

    fun delete(id: Long, cascade: Boolean) {
        try {
            if (cascade) {
                carroRepository.deleteByModeloId(id) // exclui todos os carros vinculados
            }
            modeloRepository.deleteById(id)
        } catch (e: DataIntegrityViolationException) {
            throw IllegalStateException(
                    "Não é possível excluir o modelo, existem carros vinculados.", e
            )
        }
    }
}
