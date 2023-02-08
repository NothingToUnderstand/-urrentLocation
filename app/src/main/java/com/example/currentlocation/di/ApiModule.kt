package com.example.currentlocation.di

import com.example.currentlocation.BuildConfig
import com.example.currentlocation.data.remote.DirectionsService
import com.example.currentlocation.data.remote.RemoteDataSource
import com.example.currentlocation.data.remote.RemoteDataSourceImpl
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
internal class ApiModule {

    @Provides
    @Singleton
    fun getUnsafeOkHttpClient(): OkHttpClient {
        return OkHttpClient
            .Builder()
            .addInterceptor { chain ->
                val url = chain.request().url
                    .newBuilder()
                    .addQueryParameter("key", BuildConfig.MAPS_API_KEY)
                    .build()
                val newRequest = chain.request().newBuilder().url(url).build()
                chain.proceed(newRequest)
            }
            .build()
    }

//    @Provides
//    @Singleton
//    fun getUnsafeOkHttpClient(): OkHttpClient {
//        return OkHttpClient
//            .Builder()
//            .addInterceptor { chain ->
//                val newRequest = chain.request()
//                    .newBuilder()
//                    .addHeader("key", BuildConfig.MAPS_API_KEY)
//                    .build()
//                chain.proceed(newRequest)
//            }
//            .build()
//    }

    @Provides
    @Singleton
    fun provideGson(): Gson {
        val gsonBuilder = GsonBuilder()
        gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
        return gsonBuilder.create()
    }

    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient, gson: Gson): DirectionsService {
        return Retrofit
            .Builder()
            .baseUrl("https://maps.googleapis.com/maps/api/directions/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .client(client)
            .build()
            .create(DirectionsService::class.java)
    }

    @Provides
    @Singleton
    fun providerRemoteDataSource(
        directionsApi: DirectionsService
    ): RemoteDataSource = RemoteDataSourceImpl(directionsApi)
}