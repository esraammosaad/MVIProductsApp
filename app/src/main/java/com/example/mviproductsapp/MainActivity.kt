package com.example.mviproductsapp

import DetailsScreen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mviproductsapp.data.data_sources.remote.RemoteDataSource
import com.example.mviproductsapp.data.data_sources.remote.RetrofitFactory
import com.example.mviproductsapp.data.repository.ProductRepository
import com.example.mviproductsapp.details.view_model.DetailsViewModel
import com.example.mviproductsapp.details.view_model.DetailsViewModelFactory
import com.example.mviproductsapp.home.view.HomeScreen
import com.example.mviproductsapp.home.view_model.HomeViewModel
import com.example.mviproductsapp.home.view_model.HomeViewModelFactory
import com.example.productsapp.utils.NavigationRoutes

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navigationController = rememberNavController()
            NavHost(
                navController = navigationController,
                startDestination = NavigationRoutes.HomeScreen
            ) {
                composable<NavigationRoutes.HomeScreen> { backEntry ->
                    val homeViewModelFactory = HomeViewModelFactory(
                        ProductRepository(
                            RemoteDataSource(
                                RetrofitFactory.apiService
                            )
                        )
                    )
                    val homeViewModel =
                        ViewModelProvider(
                            backEntry,
                            homeViewModelFactory
                        )[HomeViewModel::class.java]
                    HomeScreen(homeViewModel = homeViewModel, onCardClicked = { productId ->
                        navigationController.navigate(NavigationRoutes.DetailsScreen(productId))
                    })
                }
                composable<NavigationRoutes.DetailsScreen> { backStackEntry ->
                    val detailsViewModelFactory = DetailsViewModelFactory(
                        ProductRepository(
                            RemoteDataSource(
                                RetrofitFactory.apiService
                            )
                        )
                    )
                    val detailsViewModel = ViewModelProvider(
                        backStackEntry,
                        detailsViewModelFactory
                    )[DetailsViewModel::class.java]
                    DetailsScreen(detailsViewModel = detailsViewModel, onBackClick = {
                        navigationController.popBackStack()
                    })
                }
            }
        }
    }
}
