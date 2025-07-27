package com.gsm.dogsworld.screens.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ImageWithText(
    image: Int,
    label: String,
    value: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .padding(8.dp)
            .clip(RoundedCornerShape(4.dp))
            .background(Color(0xFFd3dff2)),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .padding(10.dp)
                .clip(RoundedCornerShape(4.dp))
                .background(Color(0xFF98b9ed))
        ) {
            Image(
                painter = painterResource(image),
                contentDescription = "",
                modifier = Modifier
                    .size(32.dp)
                    .padding(6.dp)
            )
        }

        Column(
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = label, fontWeight = FontWeight.Bold, fontSize = 14.sp)
            Text(text = value, fontWeight = FontWeight.Medium, fontSize = 12.sp)
        }
    }
}