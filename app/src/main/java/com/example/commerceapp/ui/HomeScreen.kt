package com.example.commerceapp.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.commerceapp.R
import com.example.commerceapp.domain.model.product.ProductPreview
import com.example.commerceapp.ui.product.CircularProgressBar
import com.example.commerceapp.ui.theme.blue1
import com.example.commerceapp.ui.theme.gray4
import com.example.commerceapp.ui.theme.pink1
import com.example.commerceapp.ui.theme.white

@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val uiState by viewModel.homeUiState.collectAsStateWithLifecycle()
    val onItemClick = { path: String -> navController.navigate("product/${path}") }

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
        if (uiState.isLoading) {
            item { ProgressIndicator() }
        }

        item(uiState.eventProducts) {
            if (uiState.eventProducts.isNotEmpty()) EventProductList(
                products = uiState.eventProducts, onItemClick = onItemClick
            )
            else EmptyProductList()
        }

        item {
            VerticalDivider(height = 16.dp)
        }

        if (uiState.products.isNotEmpty()) {
            uiState.products.forEach { rowProducts ->
                item(rowProducts) {
                    HomeItemRow(row = rowProducts, onItemClick = onItemClick)
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
            text = stringResource(R.string.home_title),
            modifier = Modifier.align(Alignment.CenterVertically),
            fontSize = 20.sp,
            fontWeight = FontWeight(1000)
        )
        IconButton(onClick = { /*TODO*/ }) {
            Icon(
                painter = painterResource(id = R.drawable.ic_shopping_cart),
                contentDescription = stringResource(R.string.cart)
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
                text = stringResource(R.string.today_deals),
                style = TextStyle(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold // 수정된 FontWeight 값
                ),
                color = colorResource(id = R.color.text_black),
                modifier = Modifier.weight(1f)
            )
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_right_arrow),
                    contentDescription = stringResource(R.string.more)
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
fun EventProductList(products: List<ProductPreview>, onItemClick: (String) -> Unit) {
    LazyRow(
        modifier = Modifier.padding(start = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(products) { product ->
            EventItemCard(productPreview = product, onItemClick)
        }
    }
}

@Composable
fun EmptyProductList() {
    Text(text = stringResource(R.string.empty_list))
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun EventItemCard(productPreview: ProductPreview, onClick: (String) -> Unit) {
    Column(
        modifier = Modifier
            .width(180.dp)
            .padding(end = 4.dp)
            .clickable { onClick(productPreview.no) }
    ) {
        GlideImage(
            modifier = Modifier
                .clip(RoundedCornerShape(8.dp))
                .fillMaxWidth()
                .aspectRatio(1f),
            model = productPreview.mainImageUrl,
            contentDescription = stringResource(R.string.product_image),
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
                text = "${productPreview.discountRate.toInt()}% ",
                style = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight(1000)
                ),
                color = blue1,
            )
            Text(
                text = insertComma(productPreview.retailPrice),
                style = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight(1000)
                ),
                color = gray4,
            )
        }
    }
}

@Composable
fun HomeItemRow(row: List<ProductPreview>, onItemClick: (String) -> Unit) {
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
                HomeItemCard(it, onItemClick)
            }
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun HomeItemCard(
    productPreview: ProductPreview,
    onClick: (String) -> Unit
) {
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(4.dp)
        .clickable { onClick(productPreview.no) }
        .background(white)
    ) {
        GlideImage(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
                .clip(RoundedCornerShape(8.dp)),
            model = productPreview.mainImageUrl,
            contentDescription = stringResource(R.string.product_image),
            contentScale = ContentScale.Crop
        )
        Text(
            text = productPreview.name.split(" ")[0].drop(1).dropLast(1),
            style = TextStyle(fontSize = 12.sp, fontWeight = FontWeight(100)),
            modifier = Modifier.padding(top = 8.dp),
            color = gray4
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
                    color = blue1,
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
                    pink1,
                    pink1,
                    pink1,
                    pink1
                ),
                modifier = Modifier
                    .padding(0.dp)
                    .height(25.dp)
                    .width(35.dp),
                contentPadding = PaddingValues(0.dp)
            ) {
                Text(
                    text = stringResource(R.string.promotions),
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
            contentDescription = stringResource(R.string.product_image),
            contentScale = ContentScale.Crop
        )
        Text(
            text = productPreview.name.split(" ")[0],
            style = TextStyle(fontSize = 12.sp, fontWeight = FontWeight(100)),
            modifier = Modifier.padding(top = 8.dp),
            color = gray4
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
                color = blue1,
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
                    pink1,
                    pink1,
                    pink1,
                    pink1
                ),
                modifier = Modifier
                    .padding(0.dp)
                    .height(25.dp)
                    .width(35.dp),
                contentPadding = PaddingValues(0.dp)
            ) {
                Text(
                    text = stringResource(R.string.promotions),
                    color = Color.White,
                    fontSize = 12.sp,
                    modifier = Modifier.padding(0.dp),
                    style = TextStyle(lineHeight = 16.sp)
                )
            }
        }
    }
}
