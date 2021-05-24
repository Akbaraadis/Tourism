package com.akbaradi.tourism.detail

import androidx.lifecycle.ViewModel
import com.akbaradi.tourism.core.data.TourismRepository
import com.akbaradi.tourism.core.data.source.local.entity.TourismEntity

class DetailTourismViewModel(private val tourismRepository: TourismRepository) : ViewModel() {
    fun setFavoriteTourism(tourism: TourismEntity, newStatus:Boolean) = tourismRepository.setFavoriteTourism(tourism, newStatus)
}

