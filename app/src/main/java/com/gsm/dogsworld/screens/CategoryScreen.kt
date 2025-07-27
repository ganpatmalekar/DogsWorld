package com.gsm.dogsworld.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.gsm.dogsworld.models.CategoryItem
import com.gsm.dogsworld.screens.components.AnimatedShimmer
import com.gsm.dogsworld.screens.components.Header
import com.gsm.dogsworld.screens.components.ItemLayout
import com.gsm.dogsworld.viewmodels.DogsViewModel

@Composable
fun CategoryScreen(onClick: (categoryName: CategoryItem) -> Unit) {
    val categoryViewModel: DogsViewModel = hiltViewModel()
    val categories = categoryViewModel.categories.collectAsState()

    Column {
        // Header - Create your header here
        Header()

        if (categories.value.isEmpty()) {
            LazyVerticalGrid(columns = GridCells.Fixed(2)) {
                items(4) {
                    AnimatedShimmer()
                }
            }
        } else {
            // Type of Dogs (i.e. Dog's Category)
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                contentPadding = PaddingValues(2.dp),
                verticalArrangement = Arrangement.Center
            ) {
                items(categories.value) {
                    ItemLayout(item = it, onClick)
                }
            }
        }
    }
}