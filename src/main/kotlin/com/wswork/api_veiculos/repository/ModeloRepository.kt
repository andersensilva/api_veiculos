package com.wswork.api_veiculos.repository

import com.wswork.api_veiculos.entity.Modelo
import jakarta.transaction.Transactional
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface ModeloRepository : JpaRepository<Modelo, Long> {

    fun countByMarcaId(marcaId: Long): Long

    @Query("SELECT m.id FROM Modelo m WHERE m.marca.id = :marcaId")
    fun findIdsByMarcaId(marcaId: Long): List<Long>

    @Modifying
    @Transactional
    @Query("DELETE FROM Modelo m WHERE m.marca.id = :marcaId")
    fun deleteByMarcaId(marcaId: Long)
}
