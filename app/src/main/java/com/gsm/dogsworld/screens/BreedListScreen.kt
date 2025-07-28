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
import com.gsm.dogsworld.models.Breed
import com.gsm.dogsworld.screens.components.AnimatedShimmer
import com.gsm.dogsworld.screens.components.ItemLayout
import com.gsm.dogsworld.screens.components.NoNetworkUI
import com.gsm.dogsworld.viewmodels.BreedsViewModel

@Composable
fun BreedListScreen(onClick: (breedItemJson: Breed) -> Unit) {
    val breedsViewModel: BreedsViewModel = hiltViewModel()
    val breeds = breedsViewModel.breeds.collectAsState()

    val isConnected = breedsViewModel.isConnected.collectAsState()

    Column {
        if (!isConnected.value) {
            NoNetworkUI()
        } else {
            if (breeds.value.isEmpty()) {
                LazyVerticalGrid(columns = GridCells.Fixed(2)) {
                    items(6) {
                        AnimatedShimmer()
                    }
                }
            } else {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    contentPadding = PaddingValues(2.dp),
                    verticalArrangement = Arrangement.Center
                ) {
                    items(breeds.value) {
                        ItemLayout(item = it, onClick)
                    }
                }
            }
        }
    }
}