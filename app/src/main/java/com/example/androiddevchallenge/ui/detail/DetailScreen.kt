package com.example.androiddevchallenge.ui.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.R
import com.rooparsh.data.network.model.Breed
import dev.chrisbanes.accompanist.coil.CoilImage


@Composable
fun DetailScreen(breed: Breed, modifier: Modifier = Modifier, onBackPressed: () -> Unit) {
    val scrollState = rememberScrollState()
    val imageOffset = (-scrollState.value * 0.2f).dp

    val iconBackgroundAlpha =
        ((scrollState.value / START_TOP_PADDING.toFloat()) * 0.2f).coerceAtMost(0.2f)

    Box {
        CoilImage(
            data = breed.image?.url ?: "",
            contentDescription = breed.name,
            contentScale = ContentScale.FillWidth,
            modifier = Modifier
                .offset(y = imageOffset)
                .height(200.dp)
                .fillMaxWidth()
        )

        BottomSheet(scrollState) {
            Text(
                "Breed Name",
                style = MaterialTheme.typography.h6,
                color = MaterialTheme.colors.primary
            )
            Text(
                text = breed.name ?: "",
                style = MaterialTheme.typography.h3,
                color = MaterialTheme.colors.primary
            )
            Spacer(Modifier.size(16.dp))
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
                Detail("Height", "${breed.height?.metric} m")
                Detail("Weight", "${breed.weight?.metric} kg")
                Detail("Life Span️", breed.lifeSpan ?: "NA")
            }
            Spacer(modifier = Modifier.size(16.dp))
            Detail(heading = "Temperament", detail = breed.temperament ?: "NA")
            Spacer(modifier = Modifier.size(8.dp))
            Section(heading = "Description:", detail = breed.description ?: "NA")
            Spacer(modifier = Modifier.size(8.dp))
        }

        IconButton(
            onClick = { onBackPressed() },
            modifier = Modifier
                .padding(8.dp)
                .background(Color.Black.copy(alpha = iconBackgroundAlpha), shape = CircleShape)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_arrow_back),
                contentDescription = null,
                modifier = Modifier
                    .size(32.dp),
                tint = Color.White
            )
        }
    }
}

