package io.dev.gcash_assessment.presentation.compose.components

import androidx.compose.foundation.background
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun TopBar(
    title: String,
    modifier: Modifier = Modifier
) {


    TopAppBar(
        title = {
            Text(text = title, color = Color.White)
        },
        modifier = modifier,
        backgroundColor = Color(0xFF66BB6A),
        elevation = 4.dp
    )
}
