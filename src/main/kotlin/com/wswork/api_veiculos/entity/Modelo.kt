package com.wswork.api_veiculos.entity

import jakarta.persistence.*

@Entity
data class Modelo(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long? = null,

        var nome: String? = null,

        var valorFipe: Double? = null,

        @ManyToOne
        @JoinColumn(name = "marca_id")
        var marca: Marca? = null
)
