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
import com.example.mviproductsapp.data.data_sources.remote.RemoteDataSourceImpl
import com.example.mviproductsapp.data.data_sources.remote.RetrofitFactory
import com.example.mviproductsapp.data.repository.ProductRepositoryImpl
import com.example.mviproductsapp.domain.usecase.GetProductByIdUseCase
import com.example.mviproductsapp.domain.usecase.GetProductUseCase
import com.example.mviproductsapp.peresentation.feature.details.view_model.DetailsViewModel
import com.example.mviproductsapp.peresentation.feature.details.view_model.DetailsViewModelFactory
import com.example.mviproductsapp.peresentation.feature.home.view.HomeScreen
import com.example.mviproductsapp.peresentation.feature.home.view_model.HomeViewModel
import com.example.mviproductsapp.peresentation.feature.home.view_model.HomeViewModelFactory
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
                        GetProductUseCase(
                            ProductRepositoryImpl(
                                RemoteDataSourceImpl(
                                    RetrofitFactory.apiService
                                )
                            )
                        )
                    )
                    val homeViewModel =
                        ViewModelProvider(
                            backEntry,
                            homeViewModelFactory
                        )[HomeViewModel::class.java]
                    HomeScreen(
                        homeViewModel = homeViewModel,
                        navigationController = navigationController
                    )
                }
                composable<NavigationRoutes.DetailsScreen> { backStackEntry ->
                    val detailsViewModelFactory = DetailsViewModelFactory(
                        GetProductByIdUseCase(
                            ProductRepositoryImpl(
                                RemoteDataSourceImpl(
                                    RetrofitFactory.apiService
                                )
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
