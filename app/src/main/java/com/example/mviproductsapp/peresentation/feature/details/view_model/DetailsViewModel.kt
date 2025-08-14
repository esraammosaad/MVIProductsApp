package com.example.mviproductsapp.peresentation.feature.details.view_model

import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.example.mviproductsapp.data.repository.ProductRepository
import com.example.mviproductsapp.peresentation.feature.details.DetailsIntent
import com.example.mviproductsapp.peresentation.feature.details.DetailsState
import com.example.productsapp.utils.NavigationRoutes
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch

class DetailsViewModel(
    private val productRepository: ProductRepository,
    saveStateHandle: SavedStateHandle
) : ViewModel() {
    val channelIntent = Channel<DetailsIntent>(Channel.UNLIMITED)

    private var _uiState: MutableStateFlow<DetailsState> = MutableStateFlow(DetailsState.Idle)
    val uiState = _uiState.asStateFlow()

    private var _message = MutableSharedFlow<String>()
    val message = _message.asSharedFlow()

    val productId = saveStateHandle.toRoute<NavigationRoutes.DetailsScreen>().productId

    init {
        processIntent()
    }

    fun processIntent() {
        viewModelScope.launch {
            channelIntent.consumeAsFlow().collect {
                when (it) {
                    is DetailsIntent.GetProduct -> {
                        getProductById()
                    }
                }
            }
        }
    }

    fun sendIntent(intent: DetailsIntent) {
        viewModelScope.launch {
            channelIntent.send(intent)
        }
    }

    fun getProductById() {
        viewModelScope.launch {
            try {
                _uiState.value = DetailsState.Loading
                val result = productRepository.getProductById(productId)
                result.catch {
                    _uiState.value = DetailsState.Failure(it.message.toString())
                }.collect {
                    _uiState.value = DetailsState.Success(it)
                }
            } catch (e: Exception) {
                _uiState.value = DetailsState.Failure(e.message.toString())
                _message.emit(e.message.toString())
            }
        }
    }
}

class DetailsViewModelFactory(private val productRepository: ProductRepository) :
    AbstractSavedStateViewModelFactory() {
    override fun <T : ViewModel> create(
        key: String,
        modelClass: Class<T>,
        handle: SavedStateHandle
    ): T {
        return DetailsViewModel(productRepository, handle) as T
    }
}