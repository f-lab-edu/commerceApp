package com.example.commerceapp.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.commerceapp.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchResultScreen(navController: NavController, keyword: String) {
    val viewModel: SearchResultViewModel = hiltViewModel()
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()
    val onItemClick = { path: String -> navController.navigate("product/${path}") }
    val focusRequester = remember { FocusRequester() }
    LaunchedEffect(Unit) {
        if (keyword.isNullOrBlank()) {
            focusRequester.requestFocus()
        } else {
            viewModel.updateKeyword(keyword)
        }
        viewModel.fetchProducts(keyword = keyword)
    }
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top
    ) {

        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .height(dimensionResource(id = androidx.appcompat.R.dimen.abc_action_bar_default_height_material)),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = { navController.navigateUp() }) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic__left_arrow),
                        contentDescription = "뒤로가기"
                    )
                }

                TextField(
                    value = uiState.value.query,
                    onValueChange = { text ->
                        viewModel.updateKeyword(text)
                    },
                    textStyle = TextStyle(
                        textDecoration = TextDecoration.None
                    ),
                    colors = TextFieldDefaults.textFieldColors(
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    ),
                    modifier = Modifier.focusRequester(focusRequester)
                )

                IconButton(onClick = { viewModel.fetchProducts(uiState.value.query) }) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic__search),
                        contentDescription = "검색"
                    )
                }
            }
        }

        if (uiState.value.products.isNotEmpty()) {
            uiState.value.products.forEach { rowProducts ->
                item(rowProducts) {
                    HomeItemRow(row = rowProducts, onItemClick = onItemClick)
                }
            }
        } else {
            item(uiState.value.products) { EmptyProductList() }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview(showBackground = true)
fun prevSearchResult() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .height(dimensionResource(id = androidx.appcompat.R.dimen.abc_action_bar_default_height_material)),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = { }) {
            Icon(
                painter = painterResource(id = R.drawable.ic__left_arrow),
                contentDescription = "뒤로가기"
            )
        }

        // Page title
        TextField(
            value = "텍스트",
            onValueChange = { text ->

            },
            textStyle = TextStyle(
                textDecoration = TextDecoration.None
            ),
            colors = TextFieldDefaults.textFieldColors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
        )

        IconButton(onClick = { }) {
            Icon(
                painter = painterResource(id = R.drawable.ic__search),
                contentDescription = "검색"
            )
        }
    }
}