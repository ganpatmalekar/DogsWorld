package com.gsm.dogsworld.screens.navigation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.google.gson.Gson
import com.gsm.dogsworld.models.Breed
import com.gsm.dogsworld.screens.BreedDetailScreen
import com.gsm.dogsworld.screens.BreedListScreen
import com.gsm.dogsworld.screens.CategoryScreen
import com.gsm.dogsworld.screens.SplashScreen
import com.gsm.dogsworld.utils.Routes

@Composable
fun DogsWorldNavigation(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Routes.splashScreen,
        builder = {
            composable(route = Routes.splashScreen) {
                SplashScreen(navController)
            }
            composable(route = Routes.categoryScreen) {
                CategoryScreen {
                    val categoryName = it.name
                    navController.navigate("${Routes.breedListScreen}/$categoryName")
                }
            }
            composable(
                route = "${Routes.breedListScreen}/{category}",
                arguments = listOf(
                    navArgument("category") {
                        type = NavType.StringType
                    }
                )) {
                BreedListScreen {
                    val breedItemJson = Gson().toJson(it)
                    navController.currentBackStackEntry?.savedStateHandle?.set(
                        "breedItem",
                        breedItemJson
                    )
                    navController.navigate(Routes.breedDetailScreen)
                }
            }
            composable(route = Routes.breedDetailScreen) {
                val breedItemJson =
                    navController.previousBackStackEntry?.savedStateHandle?.get<String>("breedItem")
                val breedItem = breedItemJson?.let {
                    Gson().fromJson(breedItemJson, Breed::class.java)
                }
                if (breedItem != null) {
                    BreedDetailScreen(breed = breedItem)
                } else {
                    Text("Breed Data Missing")
                }
            }
        })
}