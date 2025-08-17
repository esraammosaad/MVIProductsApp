package com.example.mviproductsapp.peresentation.feature.details.view.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.mviproductsapp.R
import com.example.mviproductsapp.domain.model.Product
import com.example.mviproductsapp.peresentation.feature.details.DetailsState
import com.example.productsapp.utils.Styles
import com.example.productsapp.utils.view.CustomDivider

@Composable
fun CustomProductDetails(uiState: DetailsState) {
    val productData = (uiState as DetailsState.Success).product as Product
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
    ) {
        item {
            Image(
                painter = rememberAsyncImagePainter(
                    productData.thumbnail,
                ),
                contentDescription = stringResource(R.string.product_image),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    productData.title,
                    style = Styles.testStyle20,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.weight(3f),
                )
                Text(
                    "$" + productData.price.toString(),
                    style = Styles.testStyle20,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.weight(1f)
                )
            }
            CustomDivider()
            Text(
                productData.description,
                style = Styles.testStyle16,
            )
            CustomDivider()
            Text(
                stringResource(R.string.category) + productData.category,
                style = Styles.testStyle16,
            )
            CustomDivider()
            Text(
                stringResource(R.string.brand) + productData.brand,
                style = Styles.testStyle16,
            )
            CustomDivider()
            Text(
                stringResource(R.string.rating) + productData.rating.toString(),
                style = Styles.testStyle16,
            )
            CustomDivider()
            Text(
                stringResource(R.string.stock) + productData.stock.toString(),
                style = Styles.testStyle16,
            )
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}