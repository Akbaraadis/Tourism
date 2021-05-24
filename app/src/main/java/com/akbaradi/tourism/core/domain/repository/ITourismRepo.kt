package com.akbaradi.tourism.core.domain.repository

import androidx.lifecycle.LiveData
import com.akbaradi.tourism.core.data.Resource
import com.akbaradi.tourism.core.domain.model.Tourism
import kotlinx.coroutines.flow.Flow

interface ITourismRepo {

    fun getAllTourism(): Flow<Resource<List<Tourism>>>

    fun getFavoriteTourism(): Flow<List<Tourism>>

    fun setFavoriteTourism(tourism: Tourism, state: Boolean)

}