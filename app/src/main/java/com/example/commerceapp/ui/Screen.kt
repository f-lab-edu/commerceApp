package com.example.commerceapp.ui

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.commerceapp.R

enum class BottomNavigationScreen(
    val route: String,
    @StringRes val stringResourceId: Int,
    @DrawableRes val iconResourceId: Int
) {
    Home("home", R.string.home, R.drawable.ic__home),
    Search("search", R.string.search, R.drawable.ic__search),
    Profile("profile", R.string.profile, R.drawable.ic__user)
}
