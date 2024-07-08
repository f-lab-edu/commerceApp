package com.example.commerceapp.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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

@Composable
fun SearchResultScreen(
    navController: NavController,
    keyword: String
) {
    val viewModel: SearchResultViewModel = hiltViewModel()
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()
    val onItemClick = { path: String -> navController.navigate("product/${path}") }
    val focusRequester = remember { FocusRequester() }
    LaunchedEffect(Unit) {
        if (keyword.isNullOrBlank()) {
            focusRequester.requestFocus()
        } else {
            viewModel.searchByKeyword(keyword)
        }
    }
    Column {
        SearchAppbar(
            query = uiState.value.query,
            onChange = { text -> viewModel.searchByKeyword(text) },
            onLeftIconClick = { navController.navigateUp() },
            submit = { text -> viewModel.searchByKeyword(text) },
            focusRequester = focusRequester
        )
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White),
            verticalArrangement = Arrangement.Top,
        ) {
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
}

@Composable
@Preview(showBackground = true)
fun PrevSearchResult() {
    SearchAppbar(focusRequester = FocusRequester())
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchAppbar(
    query: String = "",
    onChange: (String) -> Unit = {},
    onLeftIconClick: () -> Unit = {},
    submit: (String) -> Unit = {},
    focusRequester: FocusRequester
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .height(dimensionResource(id = androidx.appcompat.R.dimen.abc_action_bar_default_height_material)),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = { onLeftIconClick() }) {
            Icon(
                painter = painterResource(id = R.drawable.ic__left_arrow),
                contentDescription = "뒤로가기"
            )
        }

        TextField(
            value = query,
            onValueChange = { text: String ->
                onChange(text)
            },
            textStyle = TextStyle(
                textDecoration = TextDecoration.None
            ),
            colors = TextFieldDefaults.textFieldColors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            singleLine = true,
            modifier = Modifier.focusRequester(focusRequester)
        )

        IconButton(onClick = { submit(query) }) {
            Icon(
                painter = painterResource(id = R.drawable.ic__search),
                contentDescription = "검색"
            )
        }
    }
}