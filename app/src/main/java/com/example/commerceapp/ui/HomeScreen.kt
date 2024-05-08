package com.example.commerceapp.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.commerceapp.R
import com.example.commerceapp.domain.model.product.ProductPreview

@Composable
fun HomeScreen() {
    val viewModel: HomeViewModel = hiltViewModel()
    val uiState = viewModel.homeUiState.collectAsStateWithLifecycle()

    LazyColumn(
        modifier = Modifier
            .background(Color.White)
            .fillMaxWidth()
    ) {
        item {
            HomeAppbar()
            VerticalDivider(height = 8.dp)
            HeaderSection()
        }
        if (uiState.value.isLoading) {
            item { ProgressIndicator() }
        }

        item(uiState.value.eventProducts) {
            if (uiState.value.eventProducts.isNotEmpty()) EventProductList(products = uiState.value.eventProducts)
            else EmptyProductList()
        }

        item {
            VerticalDivider(height = 16.dp)
        }

        if (uiState.value.products.isNotEmpty()) {
            uiState.value.products.forEach { rowProducts ->
                item(rowProducts) {
                    HomeItemRow(rowProducts)
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
    Column(modifier = Modifier.padding(8.dp)) {
        Row(
            modifier = Modifier
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
fun EventProductList(products: List<ProductPreview>) {
    LazyRow(
        modifier = Modifier.padding(start = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
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
        modifier = Modifier
            .width(180.dp)
            .padding(end = 4.dp)
    ) {
        GlideImage(
            modifier = Modifier
                .clip(RoundedCornerShape(8.dp))
                .fillMaxWidth()
                .aspectRatio(1f),
            model = productPreview.mainImageUrl,
            contentDescription = "상품사진",
            contentScale = ContentScale.Crop
        )
        Text(
            text = productPreview.name,
            style = TextStyle(fontSize = 16.sp, color = Color.Black),
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )
        Row(
            modifier = Modifier.padding(top = 8.dp, bottom = 8.dp)
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

@Composable
fun HomeItemRow(row: List<ProductPreview>) {
    Row(
        Modifier
            .fillMaxWidth()
            .padding(4.dp)
    ) {
        row.forEach {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
            ) {
                HomeItemCard(it)
            }
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun HomeItemCard(productPreview: ProductPreview) {
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(4.dp)) {
        GlideImage(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
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
            modifier = Modifier.padding(top = 8.dp, bottom = 8.dp)
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
            modifier = Modifier.padding(bottom = 8.dp)
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

@Preview(showBackground = true, backgroundColor = 0xFFF0F0F0)
@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun PreviewItemCard() {
    val productPreview = ProductPreview(
        retailPrice = 61000,
        basePrice = 41900,
        discountedPrice = 0,
        discountRate = 31.0,
        expirationDate = "",
        isSoldOut = false,
        mainImageUrl = "https://3p-image.kurly.com/files/20231124/ec626c15-ccc1-41d9-9230-39da535ce6ba.jpg",
        name = "[샤이닝홈] 그루인 애쉬드 원형 원목 스툴 320x320 내추럴",
        no = "1000336967",
        reviewCount = 0,
        adultVerificationFailed = false
    )

    Column {
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
            Text(
                text = "${productPreview.discountRate}%  ",
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
