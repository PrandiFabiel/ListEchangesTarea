package com.example.listechangestarea.ui.Screens

import com.example.listechangestarea.data.remote.dto.DtoExchange

data class ExchangeListState(
    val isLoading: Boolean = false,
    val exchanges: List<DtoExchange> = emptyList(),
    val error: String = ""
)
