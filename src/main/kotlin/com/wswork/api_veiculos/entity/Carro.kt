package com.wswork.api_veiculos.entity

import jakarta.persistence.*

@Entity
data class Carro(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long? = null,

        var timestampCadastro: Long? = null,

        var ano: Int? = null,

        var combustivel: String? = null,

        var numPortas: Int? = null,

        var cor: String? = null,

        @ManyToOne(cascade = [CascadeType.PERSIST, CascadeType.MERGE])
        @JoinColumn(name = "modelo_id")
        var modelo: Modelo? = null
)
