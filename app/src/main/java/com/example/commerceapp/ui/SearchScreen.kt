package com.example.commerceapp.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.commerceapp.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(navController: NavController) {
    val viewModel: SearchViewModel = hiltViewModel()
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()
    val onItemClick = { path: String -> navController.navigate("search/${path}") }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top
    ) {

        Box(
            modifier = Modifier
                .height(dimensionResource(id = androidx.appcompat.R.dimen.abc_action_bar_default_height_material))
                .padding(8.dp)
                .fillMaxWidth()
        ) {
            searchBar(onItemClick)
        }
        Column(Modifier.padding(8.dp)) {

            clips(title = "최근 검색어", list = uiState.value.recent, onClick = onItemClick)

            clips(title = "추천 검색어", list = uiState.value.recommends, onClick = onItemClick)
        }
    }

}

@Composable
fun searchBar(onItemClick: (String) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 8.dp, end = 8.dp)
            .background(colorResource(id = R.color.gray_02))
            .clip(RoundedCornerShape(8.dp))
            .clickable {
                onItemClick(" ")
            },
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "검색어를 입력하세요",
            style = TextStyle(
                lineHeight = TextUnit(2.5f, TextUnitType.Em),
                platformStyle = PlatformTextStyle(includeFontPadding = false),
                lineHeightStyle = LineHeightStyle(
                    alignment = LineHeightStyle.Alignment.Center,
                    trim = LineHeightStyle.Trim.Both
                )
            ),
            modifier = Modifier
                .weight(1f)
                .padding(8.dp),
        )
        IconButton(onClick = { onItemClick(" ") }) {
            Icon(
                painter = painterResource(id = R.drawable.ic__search),
                contentDescription = "검색"
            )
        }
    }
}

@Composable
fun clips(title: String, list: List<String>, onClick: (String) -> Unit) {
    Column {
        Text(
            text = title,
            style = TextStyle(
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            ),
            color = colorResource(id = R.color.text_black),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp, bottom = 8.dp)
        )
        LazyRow(
            Modifier
                .fillMaxWidth()
                .padding(top = 8.dp, bottom = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(list) {
                SuggestionChip(
                    onClick = { onClick(it) },
                    label = { Text(text = it) },
                    shape = RoundedCornerShape(50)
                )
            }
        }
    }
}
