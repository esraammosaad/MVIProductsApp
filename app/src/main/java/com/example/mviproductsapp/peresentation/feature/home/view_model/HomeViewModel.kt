package com.example.mviproductsapp.peresentation.feature.home.view_model

import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mviproductsapp.domain.usecase.GetProductUseCase
import com.example.mviproductsapp.peresentation.feature.home.HomeEvent
import com.example.mviproductsapp.peresentation.feature.home.HomeIntent
import com.example.mviproductsapp.peresentation.feature.home.HomeState
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch

@OptIn(FlowPreview::class)
class HomeViewModel(
    private val getProductUseCase: GetProductUseCase,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    val channelIntent = Channel<HomeIntent>(Channel.UNLIMITED)
    private val _uiState = MutableStateFlow<HomeState>(HomeState.Idle)
    val uiState = _uiState.asStateFlow()

    private var _event = MutableSharedFlow<HomeEvent>()
    val event = _event.asSharedFlow()

    init {
        processIntent()
    }

    private fun processIntent() {
        viewModelScope.launch {
            channelIntent.consumeAsFlow().collect {
                when (it) {
                    is HomeIntent.GetProducts -> {
                        getProducts()
                    }

                    is HomeIntent.ProductClicked -> {
                        _event.emit(HomeEvent.NavigateToDetails(it.productId))
                    }
                }
            }
        }
    }

    fun sendIntent(intent: HomeIntent) {
        viewModelScope.launch {
            channelIntent.send(intent)
        }
    }

    fun getProducts() {
        viewModelScope.launch {
            try {
                _uiState.value = HomeState.Loading
                val result = getProductUseCase.getProducts()
                result.catch {
                    _uiState.value = HomeState.Failure(it.message.toString())
                }.collect {
                    _uiState.value = HomeState.Success(it.products)
                }
            } catch (e: Exception) {
                _uiState.value = HomeState.Failure(e.message.toString())
                _event.emit(HomeEvent.ShowToast(e.message.toString()))
            }
        }
    }
}

class HomeViewModelFactory(private val getProductUseCase: GetProductUseCase) :
    AbstractSavedStateViewModelFactory() {
    override fun <T : ViewModel> create(
        key: String,
        modelClass: Class<T>,
        handle: SavedStateHandle
    ): T {
        return HomeViewModel(getProductUseCase, handle) as T
    }
}