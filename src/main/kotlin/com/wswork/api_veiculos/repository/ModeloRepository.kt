package com.wswork.api_veiculos.repository

import com.wswork.api_veiculos.entity.Modelo
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ModeloRepository : JpaRepository<Modelo, Long>