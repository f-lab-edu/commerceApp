package com.example.commerceapp.ui

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.placeholder
import com.example.commerceapp.R
import com.example.commerceapp.domain.model.product.Product
import com.example.commerceapp.domain.model.product.ProductPreview
import com.gowtham.ratingbar.RatingBar

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun ProductScreen(navController: NavController, productNo: String) {
    val product = Product(
        retailPrice = 8000,
        basePrice = 9000,
        discountedPrice = 7000,
        discountRate = 3.5,
        expirationDate = "20200202",
        isSoldOut = false,
        mainImageUrl = "https://3p-image.kurly.com/files/20231124/ec626c15-ccc1-41d9-9230-39da535ce6ba.jpg",
        name = "상품명입니다ㅏ",
        no = "123123132",
        reviewCount = 3,
        shortDescription = "상품설명입니다",
        sellerName = "셀러1",
        adultVerificationFailed = false,
        isThirdPart = false,
        productVerticalSmallUrl = "",
        masterProductCode = "2323232323",
        masterProductName = "상품 이거 블랙",
        dealProducts = emptyList<String>(),
    )

    val previewList = listOf(
        ProductPreview(
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
    )
    Scaffold(
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
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(text = "12,345")
                }
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
        Row {
            Column(
                Modifier
                    .weight(1F)
                    .height(40.dp)
                    .align(Alignment.CenterVertically)
            ) {
                Text(
                    text = "상품정보",
                    Modifier
                        .weight(1f)
                        .padding(8.dp)
                        .align(Alignment.CenterHorizontally)
                )
                HorizontalDivider(thickness = 2.dp)
            }
            Column(
                Modifier
                    .weight(1F)
                    .height(40.dp)
                    .align(Alignment.CenterVertically)
            ) {
                Text(
                    text = "리뷰",
                    Modifier
                        .weight(1f)
                        .padding(8.dp)
                        .align(Alignment.CenterHorizontally)
                )
                HorizontalDivider(thickness = 2.dp)
            }
            Column(
                Modifier
                    .weight(1F)
                    .height(40.dp)
                    .align(Alignment.CenterVertically)
            ) {
                Text(
                    text = "연관상품",
                    Modifier
                        .weight(1f)
                        .padding(8.dp)
                        .align(Alignment.CenterHorizontally)
                )
                HorizontalDivider(thickness = 2.dp)
            }
        }
        LazyColumn(
            Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            item {
                GlideImage(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(474.dp)
                        .border(width = 1.dp, color = Color.Black),
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
                            text = "(${product.reviewCount.toString()})",
                            fontSize = 14.sp
                        )
                    }
                }
            }

            item {
                HorizontalDivider(thickness = 8.dp, color = colorResource(id = R.color.gray_01))
                Column {
                    Text(
                        text = "상품정보",
                        fontSize = 20.sp,
                        modifier = Modifier.padding(16.dp),
                        fontWeight = FontWeight.Bold
                    )
                    Text(text = " 1914년에 설립된 조선 호텔은 그 이름만큼이나 유구한 역사를 자랑합니다. 서울의 중심, 소공동에 처음 자리 잡은 후 100년이 넘는 긴 세월 동안 쌓은 고객 경험은 헤아릴 수 없을 정도랍니다. 그 역사가 증명하는 품격에는 고객 만족을 위한 수없이 많은 노력, 그 위에 빚어진 단단한 자존심이 담겨 있죠. 이제 컬리에서도 만나볼 수 있는 더 조선 호텔, 그 격조 높은 서비스를 일상에서도 누려보세요. ")
                    GlideImage(
                        modifier = Modifier
                            .fillMaxWidth()
                            .border(width = 1.dp, color = Color.Black),
                        model = "https://img-cf.kurly.com/hdims/resize/%3E1010x/quality/90/src/shop/data/goodsview/20240322/gv40000808909_1.jpg",
                        loading = placeholder(R.drawable.iv__placeholder),
                        contentDescription = "상품사진",
                        contentScale = ContentScale.Crop
                    )
                }
            }
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
                    items(previewList) { product ->
                        EventItemCard(productPreview = product, onClick = {})
                    }
                }
            }
        }
    }
}
