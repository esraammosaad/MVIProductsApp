package com.example.mviproductsapp.home.view.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.mviproductsapp.R
import com.example.mviproductsapp.data.model.Product
import com.example.productsapp.utils.Styles

@Composable
fun CustomProductItem(
    modifier: Modifier = Modifier,
    product: Product,
    onCardClick: () -> Unit
) {
    Card(
        modifier = modifier
            .fillMaxWidth(0.5f)
            .height(250.dp)
            .clickable { onCardClick() },
        colors = CardColors(
            containerColor = Color.White,
            contentColor = Color.Black,
            disabledContainerColor = Color.White,
            disabledContentColor = Color.White
        ),

        ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.TopEnd
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(6.dp)
            ) {
                Image(
                    painter = rememberAsyncImagePainter(
                        product.thumbnail,
                    ),
                    contentDescription = stringResource(R.string.product_image),
                    modifier = Modifier.size(150.dp)
                )
                Column {
                    Text(
                        product.title,
                        style = Styles.testStyle14,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1,
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        product.description, style = Styles.testStyle10,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 2
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        "$" + product.price.toString(),
                        style = Styles.testStyle16,
                    )
                }
            }
            Icon(
                if (product.isFavorite) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
                contentDescription = null,
                modifier = Modifier
                    .padding(8.dp)
                    .clickable {
                    })
        }
    }
}