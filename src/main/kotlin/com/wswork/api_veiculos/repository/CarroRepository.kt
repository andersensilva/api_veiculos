package com.wswork.api_veiculos.repository

import com.wswork.api_veiculos.entity.Carro
import jakarta.transaction.Transactional
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface CarroRepository : JpaRepository<Carro, Long> {

    fun countByModeloId(modeloId: Long): Long

    @Modifying
    @Transactional
    @Query("DELETE FROM Carro c WHERE c.modelo.id = :modeloId")
    fun deleteByModeloId(modeloId: Long)
}
