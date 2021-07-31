package com.akbaradi.tourism.core.di

import androidx.room.Room
import com.akbaradi.tourism.core.data.TourismRepository
import com.akbaradi.tourism.core.data.source.local.LocalDataSource
import com.akbaradi.tourism.core.data.source.local.room.TourismDatabase
import com.akbaradi.tourism.core.data.source.remote.RemoteDataSource
import com.akbaradi.tourism.core.data.source.remote.network.ApiService
import com.akbaradi.tourism.core.domain.repository.ITourismRepo
import com.akbaradi.tourism.core.utils.AppExecutors
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import okhttp3.CertificatePinner
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val databaseModule = module {
    factory { get<TourismDatabase>().tourismDao() }
    single {
        val passphrase: ByteArray = SQLiteDatabase.getBytes("dicoding".toCharArray())
        val factory = SupportFactory(passphrase)
        Room.databaseBuilder(
            androidContext(),
            TourismDatabase::class.java, "Tourism.db"
        ).fallbackToDestructiveMigration()
            .openHelperFactory(factory)
            .build()
    }
}

val networkModule = module {
    single {
        val hostname = "tourism-made.akbar.net"
        val certificatePinner = CertificatePinner.Builder()
            .add(hostname, "sha256/qwZ2ibJvu3/yE9I82qndDzxLjds/LkC9vc4/GFCdmR8=")
            .add(hostname, "sha256/GI75anSEdkuHj05mreE0Sd9jE6dVqUIzzXRHHlZBVbI=")
            .add(hostname, "sha256/r/mIkG3eEpVdm+u/ko/cwxzOMo1bk4TyHIlByibiA5E=")
            .build()
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .certificatePinner(certificatePinner)
            .build()
    }
    single {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://tourism-api.dicoding.dev/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
        retrofit.create(ApiService::class.java)
    }
}

val repositoryModule = module {
    single { LocalDataSource(get()) }
    single { RemoteDataSource(get()) }
    factory { AppExecutors() }
    single<ITourismRepo> { TourismRepository(get(), get(), get()) }
}