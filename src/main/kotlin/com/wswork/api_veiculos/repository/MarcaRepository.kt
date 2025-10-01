package com.wswork.api_veiculos.repository

import com.wswork.api_veiculos.entity.Marca
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface MarcaRepository : JpaRepository<Marca, Long>