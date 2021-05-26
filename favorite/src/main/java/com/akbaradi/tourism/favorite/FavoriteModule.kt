package com.akbaradi.tourism.favorite

import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

class FavoriteModule {
    val favoriteModule = module(override = true) {
        viewModel { FavoriteViewModel(get()) }
    }
}