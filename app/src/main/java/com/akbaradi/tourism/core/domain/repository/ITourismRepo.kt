package com.akbaradi.tourism.core.domain.repository

import androidx.lifecycle.LiveData
import com.akbaradi.tourism.core.data.Resource
import com.akbaradi.tourism.core.domain.model.Tourism

interface ITourismRepo {

    fun getAllTourism(): LiveData<Resource<List<Tourism>>>

    fun getFavoriteTourism(): LiveData<List<Tourism>>

    fun setFavoriteTourism(tourism: Tourism, state: Boolean)

}