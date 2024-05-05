package com.example.commerceapp.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.commerceapp.R
import com.example.commerceapp.domain.model.product.ProductPreview

@Composable
fun HomeScreen() {
    val viewModel: HomeViewModel = hiltViewModel()
    val uiState = viewModel.uiState.collectAsState()
    LazyColumn(Modifier.background(Color.White)) {
        item {
            HomeAppbar()
            Divider(height = 8.dp)
            HeaderSection()
        }
        if (uiState.value.isLoading) {
            item { ProgressIndicator() }
        } else if (uiState.value.eventProducts.isNotEmpty()) {
            item { EventProductList(products = uiState.value.eventProducts) }
        } else {
            item { EmptyProductList() }
        }
        item {
            Divider(height = 16.dp)
        }
        if (uiState.value.isLoading) {
            item { ProgressIndicator() }
        } else if (uiState.value.products.isNotEmpty()) {
            itemsIndexed(uiState.value.products.chunked(2)) { _, rowProducts ->
                BoxWithConstraints() {
                    val half = this.maxWidth / 2
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(4.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        for (product in rowProducts) {
                            HomeItemCard(productPreview = product, half)
                        }
                    }
                }
            }
        } else {
            item { EmptyProductList() }
        }
    }
}

@Composable
private fun HomeAppbar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Text(
            text = "Page title",
            modifier = Modifier.align(Alignment.CenterVertically),
            fontSize = 20.sp,
            fontWeight = FontWeight(1000)
        )
        IconButton(onClick = { /*TODO*/ }) {
            Icon(
                painter = painterResource(id = R.drawable.ic__shopping_cart),
                contentDescription = "장바구니"
            )
        }
    }
}

@Composable
fun HeaderSection() {
    Column(Modifier.padding(8.dp)) {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "오늘의 할인상품",
                style = TextStyle(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold // 수정된 FontWeight 값
                ),
                color = colorResource(id = R.color.text_black),
                modifier = Modifier.weight(1f)
            )
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic__right_arrow),
                    contentDescription = "더보기"
                )
            }
        }
    }
}


@Composable
fun ProgressIndicator() {
    CircularProgressIndicator(
        modifier = Modifier.width(64.dp),
        color = MaterialTheme.colorScheme.secondary
    )
}

@Composable
fun Divider(height: Dp) {
    Box(
        Modifier
            .background(colorResource(id = R.color.gray_01))
            .fillMaxWidth()
            .height(height)
            .padding(top = 8.dp, bottom = 8.dp)
    )
}

@Composable
fun EventProductList(products: List<ProductPreview>) {
    LazyRow(Modifier.padding(start = 8.dp)) {
        items(products) { product ->
            EventItemCard(productPreview = product)
        }
    }
}

@Composable
fun EmptyProductList() {
    Text(text = "목록이 없습니다.")
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun EventItemCard(productPreview: ProductPreview) {
    Column(
        Modifier
            .width(180.dp)
    ) {
        GlideImage(
            modifier = Modifier
                .clip(RoundedCornerShape(8.dp))
                .size(160.dp),
            model = productPreview.mainImageUrl,
            contentDescription = "상품사진",
            contentScale = ContentScale.Crop
        )
        Text(
            text = productPreview.name,
            style = TextStyle(fontSize = 16.sp, color = Color.Black),
            modifier = Modifier.fillMaxWidth(),
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )
        Row(
            Modifier.padding(top = 8.dp, bottom = 8.dp)
        ) {
            Text(
                text = "${productPreview.discountRate}% ",
                style = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight(1000)
                ),
                color = colorResource(id = R.color.blue_01),
            )
            Text(
                text = insertComma(productPreview.retailPrice),
                style = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight(1000)
                ),
                color = colorResource(id = R.color.text_light_gray),
            )
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun HomeItemCard(productPreview: ProductPreview, size: Dp) {
    Column(
        Modifier
            .width(size)
    ) {
        GlideImage(
            modifier = Modifier
                .size(180.dp)
                .clip(RoundedCornerShape(8.dp)),
            model = productPreview.mainImageUrl,
            contentDescription = "상품사진",
            contentScale = ContentScale.Crop
        )
        Text(
            text = productPreview.name.split(" ")[0],
            style = TextStyle(fontSize = 12.sp, fontWeight = FontWeight(100)),
            modifier = Modifier.padding(top = 8.dp),
            color = colorResource(id = R.color.text_light_gray)
        )
        Text(
            text = productPreview.name,
            style = TextStyle(fontSize = 16.sp, color = Color.Black),
            maxLines = 1,
            modifier = Modifier.padding(top = 8.dp)
        )
        Row(
            Modifier.padding(top = 8.dp, bottom = 8.dp)
        ) {
            if (productPreview.discountRate.toInt() > 0) {
                Text(
                    text = "${productPreview.discountRate}% ",
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontWeight = FontWeight(1000)
                    ),
                    color = colorResource(id = R.color.blue_01),
                )
            }
            Text(
                text = insertComma(productPreview.retailPrice),
                style = TextStyle(
                    fontSize = 16.sp,
                    color = Color.DarkGray,
                    fontWeight = FontWeight(1000)
                )
            )
        }
        Row(
            Modifier.padding(bottom = 8.dp)
        ) {
            Button(
                onClick = { /*TODO*/ }, shape = RoundedCornerShape(8.dp), colors = ButtonColors(
                    colorResource(id = R.color.pink_01),
                    colorResource(id = R.color.pink_01),
                    colorResource(id = R.color.pink_01),
                    colorResource(id = R.color.pink_01)
                ),
                modifier = Modifier
                    .padding(0.dp)
                    .height(25.dp)
                    .width(35.dp),
                contentPadding = PaddingValues(0.dp)
            ) {
                Text(
                    text = "특가",
                    color = Color.White,
                    fontSize = 12.sp,
                    modifier = Modifier.padding(0.dp),
                    style = TextStyle(lineHeight = 16.sp)
                )
            }
        }
    }
}

