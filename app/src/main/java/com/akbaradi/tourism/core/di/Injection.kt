package com.akbaradi.tourism.core.di

import android.content.Context

import com.akbaradi.tourism.core.data.source.local.LocalDataSource
import com.akbaradi.tourism.core.data.source.local.room.TourismDatabase

import com.akbaradi.tourism.core.data.TourismRepository
import com.akbaradi.tourism.core.data.source.remote.RemoteDataSource
import com.akbaradi.tourism.core.utils.AppExecutors
import com.akbaradi.tourism.core.utils.JsonHelper

object Injection {
    fun provideRepository(context: Context): TourismRepository {
        val database = TourismDatabase.getInstance(context)

        val remoteDataSource = RemoteDataSource.getInstance(JsonHelper(context))
        val localDataSource = LocalDataSource.getInstance(database.tourismDao())
        val appExecutors = AppExecutors()

        return TourismRepository.getInstance(remoteDataSource, localDataSource, appExecutors)
    }
}
