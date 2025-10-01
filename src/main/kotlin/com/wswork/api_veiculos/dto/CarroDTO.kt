package com.wswork.api_veiculos.dto

import com.wswork.api_veiculos.entity.Carro
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonPropertyOrder
import com.fasterxml.jackson.databind.JsonSerializer
import com.fasterxml.jackson.databind.SerializerProvider
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.fasterxml.jackson.core.JsonGenerator
import java.io.IOException
import java.text.DecimalFormat

@JsonPropertyOrder(
        "id",
        "timestamp_cadastro",
        "modelo_id",
        "ano",
        "combustivel",
        "num_portas",
        "cor",
        "nome_modelo",
        "valor"
)
class CarroDTO(carro: Carro) {

    var id: Long? = carro.id

    @JsonProperty("timestamp_cadastro")
    var timestampCadastro: Long? = carro.timestampCadastro

    @JsonProperty("modelo_id")
    var modeloId: Long? = carro.modelo?.id

    var ano: Int? = carro.ano
    var combustivel: String? = carro.combustivel

    @JsonProperty("num_portas")
    var numPortas: Int? = carro.numPortas

    var cor: String? = carro.cor

    @JsonProperty("nome_modelo")
    var nomeModelo: String? = carro.modelo?.nome

    @JsonSerialize(using = ValorSerializer::class)
    var valor: Double? = carro.modelo?.valorFipe

    // Serializer customizado para o campo "valor"
    class ValorSerializer : JsonSerializer<Double>() {
        companion object {
            private val df = DecimalFormat("#,###")
        }

        @Throws(IOException::class)
        override fun serialize(value: Double?, gen: JsonGenerator, serializers: SerializerProvider) {
            gen.writeString(df.format(value))
        }
    }
}
