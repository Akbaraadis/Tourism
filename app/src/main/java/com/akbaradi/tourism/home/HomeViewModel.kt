package com.akbaradi.tourism.home

import androidx.lifecycle.ViewModel
import com.akbaradi.tourism.core.data.TourismRepository

class HomeViewModel(tourismRepository: TourismRepository) : ViewModel() {

    val tourism = tourismRepository.getAllTourism()

}

