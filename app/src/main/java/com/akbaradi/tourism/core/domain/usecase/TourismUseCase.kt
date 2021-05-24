package com.akbaradi.tourism.core.domain.usecase

import androidx.lifecycle.LiveData
import com.akbaradi.tourism.core.data.Resource
import com.akbaradi.tourism.core.domain.model.Tourism

interface TourismUseCase {
    fun getAllTourism(): LiveData<Resource<List<Tourism>>>
    fun getFavoriteTourism(): LiveData<List<Tourism>>
    fun setFavoriteTourism(tourism: Tourism, state: Boolean)
}