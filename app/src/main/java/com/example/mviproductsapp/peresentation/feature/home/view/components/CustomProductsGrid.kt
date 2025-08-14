package com.example.mviproductsapp.peresentation.feature.home.view.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells.Fixed
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.mviproductsapp.R
import com.example.mviproductsapp.data.model.Product
import com.example.mviproductsapp.peresentation.feature.home.HomeState
import com.example.productsapp.utils.Styles

@Composable
fun CustomProductsGrid(
    uiState: HomeState,
    onCardClick: (Int) -> Unit
) {
    val productsList =
        (uiState as HomeState.Success).products as List<Product>
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