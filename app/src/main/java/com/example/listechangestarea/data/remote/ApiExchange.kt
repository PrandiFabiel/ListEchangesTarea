package com.example.listechangestarea.data.remote

import com.example.listechangestarea.data.remote.dto.DtoExchange
import retrofit2.http.GET

interface ExchangeApi {
    @GET("/v1/exchanges")
    suspend fun getExchanges(): List<DtoExchange>
}