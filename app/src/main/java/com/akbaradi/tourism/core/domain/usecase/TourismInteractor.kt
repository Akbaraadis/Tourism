package com.akbaradi.tourism.core.domain.usecase

import com.akbaradi.tourism.core.domain.model.Tourism
import com.akbaradi.tourism.core.domain.repository.ITourismRepo

class TourismInteractor(private val tourismRepository: ITourismRepo): TourismUseCase {

    override fun getAllTourism() = tourismRepository.getAllTourism()

    override fun getFavoriteTourism() = tourismRepository.getFavoriteTourism()

    override fun setFavoriteTourism(tourism: Tourism, state: Boolean) = tourismRepository.setFavoriteTourism(tourism, state)
}