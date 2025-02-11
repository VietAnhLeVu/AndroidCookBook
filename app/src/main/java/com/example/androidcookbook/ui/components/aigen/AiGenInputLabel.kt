package com.example.androidcookbook.ui.components.aigen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.androidcookbook.R

@Composable
fun AiGenInputLabel(modifier: Modifier = Modifier,imageResource: Int, title: String, contentDescription: String = "") {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Image(
            modifier = Modifier.size(18.dp),
            painter = painterResource(imageResource),
            contentDescription = contentDescription,
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.size(2.dp))
        Text(
            text = title,
            fontFamily = FontFamily(Font(R.font.nunito_regular)),
            fontWeight = FontWeight.Bold
        )
    }
}