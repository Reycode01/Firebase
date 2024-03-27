package dev.korryr.bongesha.commons

import android.media.Image
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import dev.korryr.bongesha.ui.theme.orange100

@Composable
fun BongaBox(
    modifier: Modifier,
    painter: Painter,
    onClick: (() -> Unit)? = null,
    text: String? = null,
    tint: Color = Color.LightGray,
    clickedColor: Color = orange100
){
    var clicked by remember {
        mutableStateOf(false)
    }
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .clip(RoundedCornerShape(12.dp))
            .border(
                1.dp,
                color = orange100,
                shape = RoundedCornerShape(12.dp)
            )
            .background(
                color = Color.Transparent,
                shape = RoundedCornerShape(12.dp)
            )
            .size(54.dp),


    ){

        Column {
            Image(
                contentDescription = null,
                painter = painter,
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .background(Color.Transparent)
                    .size(24.dp)
            )
            if (text != null) {
                Text(text = text)
            }
        }

    }
}