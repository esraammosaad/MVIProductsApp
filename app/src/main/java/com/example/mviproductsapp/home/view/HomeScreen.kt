package com.example.mviproductsapp.home.view

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.mviproductsapp.home.HomeIntent
import com.example.mviproductsapp.home.HomeState
import com.example.mviproductsapp.home.view.components.CustomProductsGrid
import com.example.mviproductsapp.home.view_model.HomeViewModel
import com.example.mviproductsapp.utils.view.CustomIdle
import com.example.productsapp.utils.view.CustomError
import com.example.productsapp.utils.view.CustomLoading

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    homeViewModel: HomeViewModel,
    onCardClicked: (Int) -> Unit,
) {

    val context = LocalContext.current
    val uiState = homeViewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        homeViewModel.sendIntent(HomeIntent.GetProducts)
        homeViewModel.message.collect {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
        }
    }

    Column(
        modifier = modifier
            .padding(vertical = 50.dp, horizontal = 16.dp)
    ) {
        when (uiState.value) {
            HomeState.Idle -> {
                CustomIdle()
            }

            is HomeState.Loading -> {
                CustomLoading()
            }

            is HomeState.Failure -> {
                val error = (uiState.value as HomeState.Failure).message
                CustomError(error)
            }

            is HomeState.Success -> {
                CustomProductsGrid(uiState.value, onCardClick = onCardClicked)
            }
        }
    }
}


