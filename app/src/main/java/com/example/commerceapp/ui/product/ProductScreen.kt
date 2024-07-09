package com.example.commerceapp.ui.product

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow.Companion.Ellipsis
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.placeholder
import com.example.commerceapp.R
import com.example.commerceapp.domain.model.product.Product
import com.example.commerceapp.ui.EventItemCard
import com.example.commerceapp.ui.insertComma
import com.gowtham.ratingbar.RatingBar

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun ProductScreen(
    navController: NavController,
    productNo: String
) {

    val viewModel: ProductViewModel = hiltViewModel()
    val uiState = viewModel.productUiState.collectAsStateWithLifecycle()
    val onItemClick = { path: String -> navController.navigate("product/${path}") }

    LaunchedEffect(Unit) {
        viewModel.getProduct(productNo)
    }

    Scaffold(
        topBar = { TopAppbar(navController, uiState.value.product.name) },
        bottomBar = {
            Row(
                modifier = Modifier
                    .padding(8.dp),
                verticalAlignment = Alignment.Bottom
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic__heart),
                        contentDescription = "북마크 수"
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(text = "12,345")
                }
                Spacer(modifier = Modifier.width(4.dp))
                Button(
                    colors = ButtonColors(
                        containerColor = colorResource(id = R.color.blue_01),
                        contentColor = colorResource(R.color.white),
                        disabledContentColor = colorResource(R.color.white),
                        disabledContainerColor = colorResource(R.color.text_light_gray2)
                    ),
                    onClick = {},
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(45.dp)
                ) {
                    Text(text = "구매하기")
                }
            }
        }
    ) {
        LazyColumn(
            Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            item(uiState.value.isLoading) {
                TopContents(uiState.value.product)
            }

            item(uiState.value.product.no) {
                HorizontalDivider(thickness = 8.dp, color = colorResource(id = R.color.gray_01))
                Column {
                    Text(
                        text = "상품정보",
                        fontSize = 20.sp,
                        modifier = Modifier.padding(16.dp),
                        fontWeight = FontWeight.Bold
                    )
                    Text(text = uiState.value.product.shortDescription)
                    GlideImage(
                        modifier = Modifier
                            .fillMaxWidth(),
                        model = uiState.value.product.productVerticalSmallUrl,
                        loading = placeholder(R.drawable.iv__placeholder),
                        contentDescription = "상품사진",
                        contentScale = ContentScale.Crop
                    )
                }
            }
            if (uiState.value.isLoading == ProductViewModel.ProductState.SUCCESS) {
                item {
                    Text(
                        text = "관련상품",
                        fontSize = 20.sp,
                        modifier = Modifier.padding(16.dp),
                        fontWeight = FontWeight.Bold
                    )
                    LazyRow(
                        modifier = Modifier.padding(start = 8.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(uiState.value.relatedProducts) { product ->
                            EventItemCard(
                                productPreview = product,
                                onClick = { onItemClick(uiState.value.product.no) })
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun TopAppbar(
    navController: NavController,
    title: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .background(Color.White),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(
            onClick = { navController.navigateUp() },
            modifier = Modifier.size(24.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic__left_arrow),
                contentDescription = "장바구니"
            )
        }
        Text(
            text = title,
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .weight(1f)
                .padding(start = 8.dp, end = 8.dp),
            fontSize = 20.sp,
            fontWeight = FontWeight(1000),
            overflow = Ellipsis,
            maxLines = 1,
        )
        IconButton(
            onClick = { /*TODO*/ },
            modifier = Modifier.size(24.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic__shopping_cart),
                contentDescription = "장바구니"
            )
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun TopContents(product: Product) {
    GlideImage(
        modifier = Modifier
            .fillMaxWidth()
            .height(474.dp),
        model = product.mainImageUrl,
        contentDescription = "상품사진",
        contentScale = ContentScale.Crop
    )
    Column(
        Modifier.padding(16.dp)
    ) {
        Text(
            text = product.sellerName,
            color = colorResource(id = R.color.text_light_gray2)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = product.name,
            fontSize = 24.sp,
            fontWeight = FontWeight.ExtraBold,
            lineHeight = 30.sp,
            maxLines = 2
        )
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            RatingBar(
                value = product.discountRate.toFloat(),
                painterEmpty = painterResource(id = R.drawable.star),
                painterFilled = painterResource(id = R.drawable.star_fill),
                onValueChange = {},
                onRatingChanged = {},
                spaceBetween = 0.dp,
                size = 24.dp
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "(${product.reviewCount})",
                fontSize = 14.sp
            )
        }
        Row(Modifier.padding(top = 8.dp, bottom = 8.dp)) {
            if (product.basePrice != 0) {
                Text(
                    text = "${product.discountRate.toInt()}% ",
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontWeight = FontWeight(500)
                    ),
                    color = colorResource(id = R.color.blue_01),
                )
            }
            Text(
                text = insertComma(product.retailPrice),
                style = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight(500)
                ),
                color = colorResource(id = R.color.text_light_gray),
            )
        }
        Text(
            text = insertComma(product.basePrice) + "원",
            style = TextStyle(
                fontSize = 24.sp,
                fontWeight = FontWeight(1000)
            ),
            color = colorResource(id = R.color.black),
        )
    }
}