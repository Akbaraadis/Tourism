package com.akbaradi.tourism.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.akbaradi.tourism.core.domain.usecase.TourismUseCase

class FavoriteViewModel (tourismUseCase: TourismUseCase) : ViewModel() {

    val favoriteTourism = tourismUseCase.getFavoriteTourism().asLiveData()

}
