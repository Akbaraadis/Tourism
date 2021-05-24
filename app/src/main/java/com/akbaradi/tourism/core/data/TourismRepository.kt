package com.akbaradi.tourism.core.data

import androidx.lifecycle.LiveData
import com.akbaradi.tourism.core.data.source.remote.network.ApiResponse
import com.akbaradi.tourism.core.data.source.local.LocalDataSource
import com.akbaradi.tourism.core.data.source.local.entity.TourismEntity
import com.akbaradi.tourism.core.data.source.remote.RemoteDataSource
import com.akbaradi.tourism.core.data.source.remote.response.TourismResponse
import com.akbaradi.tourism.core.utils.AppExecutors
import com.akbaradi.tourism.core.utils.DataMapper

class TourismRepository private constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) {

    companion object {
        @Volatile
        private var instance: TourismRepository? = null

        fun getInstance(
            remoteData: RemoteDataSource,
            localData: LocalDataSource,
            appExecutors: AppExecutors
        ): TourismRepository =
            instance ?: synchronized(this) {
                instance ?: TourismRepository(remoteData, localData, appExecutors)
            }
    }

    fun getAllTourism(): LiveData<Resource<List<TourismEntity>>> =
        object : NetworkBoundResource<List<TourismEntity>, List<TourismResponse>>(appExecutors) {
            override fun loadFromDB(): LiveData<List<TourismEntity>> {
                return localDataSource.getAllTourism()
            }

            override fun shouldFetch(data: List<TourismEntity>?): Boolean =
                data == null || data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<List<TourismResponse>>> =
                remoteDataSource.getAllTourism()

            override fun saveCallResult(data: List<TourismResponse>) {
                val tourismList = DataMapper.mapResponsesToEntities(data)
                localDataSource.insertTourism(tourismList)
            }
        }.asLiveData()

    fun getFavoriteTourism(): LiveData<List<TourismEntity>> {
        return localDataSource.getFavoriteTourism()
    }

    fun setFavoriteTourism(tourism: TourismEntity, state: Boolean) {
        appExecutors.diskIO().execute { localDataSource.setFavoriteTourism(tourism, state) }
    }
}

