package com.akbaradi.tourism.detail

import androidx.lifecycle.ViewModel
import com.akbaradi.tourism.core.domain.model.Tourism
import com.akbaradi.tourism.core.domain.usecase.TourismUseCase

class DetailTourismViewModel(private val tourismUseCase: TourismUseCase) : ViewModel() {
    fun setFavoriteTourism(tourism: Tourism, newStatus:Boolean) =
            tourismUseCase.setFavoriteTourism(tourism, newStatus)
}

