package com.wswork.api_veiculos.entity

import jakarta.persistence.*

@Entity
data class Marca(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long? = null,

        var nomeMarca: String? = null
)
