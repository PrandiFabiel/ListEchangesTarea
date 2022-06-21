package com.example.listechangestarea.data.remote.dto

data class DtoExchange(
    val id: String = "",
    val name: String = "",
    val description: String? = "",
    val active: Boolean = false,
    val last_updated: String = ""
)
