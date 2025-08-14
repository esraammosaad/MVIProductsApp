package com.example.mviproductsapp.home.view

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells.Fixed
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.example.mviproductsapp.R
import com.example.mviproductsapp.data.model.Product
import com.example.mviproductsapp.home.HomeIntent
import com.example.mviproductsapp.home.HomeState
import com.example.mviproductsapp.home.view_model.HomeViewModel
import com.example.mviproductsapp.home.view.components.CustomProductItem
import com.example.productsapp.utils.NavigationRoutes
import com.example.productsapp.utils.Styles
import com.example.productsapp.utils.view.CustomError
import com.example.productsapp.utils.view.CustomLoading

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    homeViewModel: HomeViewModel,
    navController: NavHostController,
) {

    val context = LocalContext.current
    val products = homeViewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        homeViewModel.channelIntent.send(HomeIntent.GetProducts)
        homeViewModel.message.collect {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
        }
    }

    Column(
        modifier = modifier
            .padding(vertical = 50.dp, horizontal = 16.dp)
    ) {
        when (products.value) {
            HomeState.Idle -> {}

            is HomeState.Loading -> {
                CustomLoading()
            }

            is HomeState.Failure -> {
                val error = (products.value as HomeState.Failure).message
                CustomError(error)
            }

            is HomeState.Success -> {
                CustomProductsGrid(products.value, onCardClick = {
                    homeViewModel.channelIntent.trySend(HomeIntent.SelectProduct(it))
                })
            }

            is HomeState.SelectProduct -> {
                val productId = (products.value as HomeState.SelectProduct).id
                navController.navigate(NavigationRoutes.DetailsScreen(productId))
            }
        }
    }
}

@Composable
private fun CustomProductsGrid(
    products: HomeState,
    onCardClick: (Int) -> Unit
) {
    val productsList =
        (products as HomeState.Success).products as List<Product>
    Text(
        stringResource(R.string.products),
        style = Styles.testStyle28
    )
    Spacer(modifier = Modifier.height(6.dp))
    Text(
        stringResource(R.string.products_found, productsList.size),
        style = Styles.testStyle14
    )
    Spacer(modifier = Modifier.height(18.dp))
    LazyVerticalStaggeredGrid(
        columns = Fixed(2),
        modifier = Modifier.fillMaxSize(),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalItemSpacing = 16.dp
    ) {
        items(productsList.size, key = { index ->
            productsList[index].id
        }) { index ->
            CustomProductItem(
                modifier = Modifier.animateItem(),
                product = productsList[index],
                onCardClick = {
                    onCardClick(productsList[index].id)
                })
        }
    }
}


