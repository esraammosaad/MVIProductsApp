package com.example.mviproductsapp.details.view_model

import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.example.mviproductsapp.data.repository.ProductRepository
import com.example.mviproductsapp.details.DetailsState
import com.example.productsapp.utils.NavigationRoutes
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class DetailsViewModel(private val productRepository: ProductRepository, saveStateHandle: SavedStateHandle) : ViewModel() {

    private var _message = MutableSharedFlow<String>()
    val message = _message.asSharedFlow()

    private var _uiState: MutableStateFlow<DetailsState> = MutableStateFlow(DetailsState.Loading)
    val uiState = _uiState.asStateFlow()

    val productId = saveStateHandle.toRoute<NavigationRoutes.DetailsScreen>().productId

    init {
        getProductById()
    }

    fun getProductById() {

        viewModelScope.launch {
            try {
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