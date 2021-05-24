package com.akbaradi.tourism.home

import androidx.lifecycle.ViewModel
import com.akbaradi.tourism.core.data.TourismRepository
import com.akbaradi.tourism.core.domain.usecase.TourismUseCase

class HomeViewModel(tourismUseCase: TourismUseCase) : ViewModel() {

    val tourism = tourismUseCase.getAllTourism()

}

