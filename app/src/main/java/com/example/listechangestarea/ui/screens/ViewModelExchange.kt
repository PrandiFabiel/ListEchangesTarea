package com.example.listechangestarea.ui.screens

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.listechangestarea.data.remote.RepositoryExchange
import com.example.listechangestarea.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class ViewModelExchange @Inject constructor(
    private val exchangesRepository: RepositoryExchange
) : ViewModel() {

    private val _state = mutableStateOf(ExchangeListState())
    val state: State<ExchangeListState> = _state

    init {
        exchangesRepository.getExchanges().onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    _state.value = ExchangeListState(isLoading = true)
                }
                is Resource.Success -> {
                    _state.value = ExchangeListState(exchanges = result.data ?: emptyList())
                }
                is Resource.Error -> {
                    _state.value = ExchangeListState(error = result.message ?: "Error")
                }
            }
        }.launchIn(viewModelScope)
    }

}