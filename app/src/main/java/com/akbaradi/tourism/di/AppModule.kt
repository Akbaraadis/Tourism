package com.akbaradi.tourism.di

import com.akbaradi.tourism.core.domain.usecase.TourismInteractor
import com.akbaradi.tourism.core.domain.usecase.TourismUseCase
import com.akbaradi.tourism.detail.DetailTourismViewModel
import com.akbaradi.tourism.home.HomeViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<TourismUseCase> { TourismInteractor(get()) }
}

val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { DetailTourismViewModel(get()) }
}