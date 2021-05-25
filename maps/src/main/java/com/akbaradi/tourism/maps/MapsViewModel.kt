package com.akbaradi.tourism.maps

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.akbaradi.tourism.core.domain.usecase.TourismUseCase

class MapsViewModel(tourismUseCase: TourismUseCase) : ViewModel() {
    val tourism = tourismUseCase.getAllTourism().asLiveData()
}