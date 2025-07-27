package com.gsm.dogsworld

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.core.content.ContextCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.google.gson.Gson
import com.gsm.dogsworld.models.Breed
import com.gsm.dogsworld.screens.navigation.DogsWorldNavigation
import com.gsm.dogsworld.ui.theme.DogsWorldTheme
import com.gsm.dogsworld.utils.Routes
import dagger.hilt.android.AndroidEntryPoint

// Important Links
// https://haikei.app/ - freely generate svg
// https://jsonbin.io/ - generate mock apis


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Set system bars appearance
        WindowCompat.setDecorFitsSystemWindows(
            window,
            false
        ) // prevents system bars from reserving space

        // Set status bar color using decorView background trick
        window.decorView.setBackgroundColor(
            ContextCompat.getColor(
                this,
                R.color.black
            )
        )

        // Control icon color (light/dark)
        WindowInsetsControllerCompat(window, window.decorView).isAppearanceLightStatusBars = false
        // false = light icons (white), true = dark icons (black)

        setContent {
            DogsWorldTheme {
                val navController = rememberNavController()
                val currentBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = currentBackStackEntry?.destination?.route
                val categoryName = currentBackStackEntry?.arguments?.getString("category")

                val breedItemJson =
                    navController.previousBackStackEntry?.savedStateHandle?.get<String>("breedItem")
                val breedItem = breedItemJson?.let {
                    Gson().fromJson(breedItemJson, Breed::class.java)
                }

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        when {
                            currentRoute == Routes.categoryScreen -> {
                                TopAppBar(
                                    title = {
                                        Text(text = resources.getString(R.string.app_name))
                                    },
                                    colors = TopAppBarDefaults.topAppBarColors(
                                        containerColor = Color.Black,
                                        titleContentColor = Color.White
                                    )
                                )
                            }

                            currentRoute?.startsWith(Routes.breedListScreen) == true -> {
                                TopAppBar(
                                    title = {
                                        Text(text = categoryName ?: "Breeds")
                                    },
                                    navigationIcon = {
                                        IconButton(onClick = { navController.popBackStack() }) {
                                            Icon(
                                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                                contentDescription = "Back",
                                                tint = Color.White
                                            )
                                        }
                                    },
                                    colors = TopAppBarDefaults.topAppBarColors(
                                        containerColor = Color.Black,
                                        titleContentColor = Color.White
                                    )
                                )
                            }

                            currentRoute == Routes.breedDetailScreen -> {
                                TopAppBar(
                                    title = {
                                        Text(text = breedItem?.name ?: "Breed Details")
                                    },
                                    navigationIcon = {
                                        IconButton(onClick = { navController.popBackStack() }) {
                                            Icon(
                                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                                contentDescription = "Back",
                                                tint = Color.White
                                            )
                                        }
                                    },
                                    colors = TopAppBarDefaults.topAppBarColors(
                                        containerColor = Color.Black,
                                        titleContentColor = Color.White
                                    )
                                )
                            }
                        }

                    }) { innerPadding ->
                    Box(modifier = Modifier.padding(innerPadding)) {
                        DogsWorldNavigation(navController = navController)
                    }
                }
            }
        }
    }
}