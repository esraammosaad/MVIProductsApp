import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.rememberAsyncImagePainter
import com.example.mviproductsapp.R
import com.example.mviproductsapp.data.model.Product
import com.example.mviproductsapp.details.DetailsState
import com.example.mviproductsapp.details.view_model.DetailsViewModel
import com.example.productsapp.utils.Styles
import com.example.productsapp.utils.view.CustomDivider
import com.example.productsapp.utils.view.CustomError
import com.example.productsapp.utils.view.CustomLoading


@Composable
fun DetailsScreen(detailsViewModel: DetailsViewModel, onBackClick: () -> Unit) {

    val product = detailsViewModel.uiState.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 50.dp, horizontal = 16.dp)
    ) {
        Row {
            Icon(
                Icons.Default.KeyboardArrowLeft,
                contentDescription = stringResource(R.string.back_icon),
                modifier = Modifier
                    .size(36.dp)
                    .clickable {
                        onBackClick()
                    }
            )
        }
        when (product.value) {
            is DetailsState.Loading -> {
                CustomLoading()
            }

            is DetailsState.Failure -> {
                val error = (product.value as DetailsState.Failure).message
                CustomError(error)

            }

            is DetailsState.Success -> {
                val productData = (product.value as DetailsState.Success).product as Product
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
            DetailsState.Idle -> {}
        }
    }
}

