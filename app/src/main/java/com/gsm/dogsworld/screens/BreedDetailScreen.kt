package com.gsm.dogsworld.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.gsm.dogsworld.R
import com.gsm.dogsworld.models.Breed
import com.gsm.dogsworld.screens.components.ImageWithText

@Composable
fun BreedDetailScreen(breed: Breed) {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
    ) {
        Card(
            modifier = Modifier.padding(10.dp),
            elevation = CardDefaults.cardElevation(4.dp),
            shape = RoundedCornerShape(6.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White)
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(data = breed.imageUrl)
                    .crossfade(true)
                    .build(),
                contentDescription = "",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
                    .clip(RectangleShape),
                contentScale = ContentScale.FillBounds,
                placeholder = painterResource(R.drawable.ic_placeholder),
                error = painterResource(R.drawable.ic_error_image)
            )

            Row(modifier = Modifier.fillMaxWidth()) {
                ImageWithText(
                    R.drawable.ic_name,
                    "Name",
                    breed.name,
                    modifier = Modifier.weight(1f)
                )
                ImageWithText(
                    R.drawable.ic_origin,
                    "Origin",
                    breed.origin,
                    modifier = Modifier.weight(1f)
                )
            }

            Row(modifier = Modifier.fillMaxWidth()) {
                ImageWithText(
                    R.drawable.ic_height,
                    "Height",
                    breed.height,
                    modifier = Modifier.weight(1f)
                )
                ImageWithText(
                    R.drawable.ic_weight,
                    "Weight",
                    breed.weight,
                    modifier = Modifier.weight(1f)
                )
            }

            Row(modifier = Modifier.fillMaxWidth()) {
                ImageWithText(
                    R.drawable.ic_lifespan,
                    "Lifespan",
                    breed.lifespan,
                    modifier = Modifier.weight(1f)
                )
                ImageWithText(
                    R.drawable.ic_group,
                    "Group",
                    breed.group,
                    modifier = Modifier.weight(1f)
                )
            }
        }

        // Card for description
        Card(
            modifier = Modifier.padding(10.dp),
            elevation = CardDefaults.cardElevation(4.dp),
            shape = RoundedCornerShape(6.dp),
            colors = CardDefaults.cardColors(Color.White)
        ) {
            Column(modifier = Modifier.padding(8.dp)) {
                Text(text = "Description", fontWeight = FontWeight.Bold, fontSize = 14.sp)
                Text(text = breed.description, fontWeight = FontWeight.Medium, fontSize = 12.sp)
            }
        }
    }
}