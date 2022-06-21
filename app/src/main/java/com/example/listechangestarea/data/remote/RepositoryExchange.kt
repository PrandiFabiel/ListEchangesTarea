package com.example.listechangestarea.data.remote

import com.example.listechangestarea.utils.Resource
import com.example.listechangestarea.data.remote.dto.DtoExchange
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject


class RepositoryExchange @Inject constructor(
    private val api: ExchangeApi
) {
    fun getExchanges(): Flow<Resource<List<DtoExchange>>> = flow {
        try {
            emit(Resource.Loading())

            val exchanges = api.getExchanges()

            emit(Resource.Success(exchanges))

        } catch (e: HttpException) {
            emit(Resource.Error(e.message ?: "Error HTTP general"))
        } catch (e: IOException) {
            emit(Resource.Error(e.message ?: "Error intentando conectarse a internet..."))
        }

    }
}