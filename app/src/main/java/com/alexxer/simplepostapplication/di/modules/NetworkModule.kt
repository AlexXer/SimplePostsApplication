package com.alexxer.simplepostapplication.di.modules

import com.alexxer.simplepostapplication.data.network.service.PostServiceApi
import com.alexxer.simplepostapplication.data.network.service.UserServiceApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule {

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()


    @Provides
    @Singleton
    fun providePostServiceApi(retrofit: Retrofit): PostServiceApi =
        retrofit.create(PostServiceApi::class.java)

    @Provides
    @Singleton
    fun provideUserServiceApi(retrofit: Retrofit): UserServiceApi =
        retrofit.create(UserServiceApi::class.java)


    private companion object {
        private const val BASE_URL = "https://jsonplaceholder.typicode.com/"
    }
}