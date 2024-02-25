package com.example.commerceapp.presentation.home

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.commerceapp.presentation.ui.component.ThemedTopbar
import timber.log.Timber
import javax.inject.Inject

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController, viewModel: HomeViewModel = hiltViewModel()) {
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }
    Scaffold(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        topBar = { ThemedTopbar() },
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) {
        it
        val uiState by viewModel.uiState.collectAsStateWithLifecycle()
        LaunchedEffect(key1 = uiState.message, block = {
            if (!uiState.message.isNullOrEmpty()) snackbarHostState.showSnackbar(message = uiState.message!!)
        })

        Timber.d("didfdsf>>>")

        if (!uiState.popularItems.isNullOrEmpty()) Log.e("tag", "${uiState.popularItems.size}")
        if (uiState.isLoading) {
            Text(text = "로딩중", modifier = Modifier.size(50.dp))
            Log.e("tag", "${uiState.popularItems.size}")
        }

        LazyColumn {
            items(uiState.popularItems) {
                Text(text = it.name?:"",
                    Modifier
                        .fillMaxWidth()
                        .height(100.dp))
            }

        }

    }

}