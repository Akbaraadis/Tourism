package com.akbaradi.tourism.favorite

import androidx.lifecycle.ViewModel
import com.akbaradi.tourism.core.data.TourismRepository

class FavoriteViewModel(tourismRepository: TourismRepository) : ViewModel() {

    val favoriteTourism = tourismRepository.getFavoriteTourism()

}

