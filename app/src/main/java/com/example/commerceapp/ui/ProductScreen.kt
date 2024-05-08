package com.example.commerceapp.ui

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun ProductScreen(navController: NavController, productNo:String) {
    Column(Modifier.fillMaxSize()) {
        GlideImage(
            modifier = Modifier
                .fillMaxWidth()
                .height(474.dp)
                .border(width = 1.dp, color = Color.Black),
            model = "https://3p-image.kurly.com/files/20231124/ec626c15-ccc1-41d9-9230-39da535ce6ba.jpg",
            contentDescription = "상품사진",
            contentScale = ContentScale.Crop
        )
        Column {
            Text(text = "no $productNo")
            Text(text = "brand")
            Text(text = "상품명")
            Text(text = "평점")
            Text(text = "리뷰개수")
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Preview(showBackground = true)
@Composable
fun ProductPrev() {
    Column(Modifier.fillMaxSize()) {
        GlideImage(
            modifier = Modifier
                .fillMaxWidth()
                .height(474.dp)
                .border(width = 1.dp, color = Color.Black),
            model = "https://3p-image.kurly.com/files/20231124/ec626c15-ccc1-41d9-9230-39da535ce6ba.jpg",
            contentDescription = "상품사진",
            contentScale = ContentScale.Crop
        )
        Column {
            Text(text = "brand")
            Text(text = "상품명")
            Text(text = "평점")
            Text(text = "리뷰개수")
        }
    }
}