package com.wswork.api_veiculos.service

import com.wswork.api_veiculos.entity.Marca
import com.wswork.api_veiculos.repository.CarroRepository
import com.wswork.api_veiculos.repository.MarcaRepository
import com.wswork.api_veiculos.repository.ModeloRepository
import jakarta.transaction.Transactional
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class MarcaService(
        private val marcaRepository: MarcaRepository,
        private val modeloRepository: ModeloRepository,
        private val carroRepository: CarroRepository
) {

    fun findAll(): List<Marca> = marcaRepository.findAll()

    fun countModelosVinculados(marcaId: Long): Long = modeloRepository.countByMarcaId(marcaId)

    fun findById(id: Long) = marcaRepository.findById(id)

    fun save(marca: Marca): Marca = marcaRepository.save(marca)

    @Transactional
    fun delete(id: Long, cascade: Boolean) {
        try {
            val modelosCount = modeloRepository.countByMarcaId(id)
            if (modelosCount > 0) {
                if (cascade) {
                    // Deleta todos os carros vinculados a modelos dessa marca
                    val modeloIds = modeloRepository.findIdsByMarcaId(id)
                    modeloIds.forEach { modeloId -> carroRepository.deleteByModeloId(modeloId) }
                    // Deleta os modelos
                    modeloRepository.deleteByMarcaId(id)
                } else {
                    throw DataIntegrityViolationException(
                            "Existem modelos vinculados a essa marca. Use cascade=true para excluir tudo."
                    )
                }
            }
            marcaRepository.deleteById(id)
        } catch (e: DataIntegrityViolationException) {
            throw ResponseStatusException(HttpStatus.CONFLICT, e.message)
        }
    }
}
