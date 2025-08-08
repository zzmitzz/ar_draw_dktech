package com.dktech.baseandroidviewdktech.di

import com.dktech.baseandroidviewdktech.data.datasource.remote.PaintingService
import com.dktech.baseandroidviewdktech.utils.Constant
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        val authentication = """
        {
            "sign": "${Constant.SIGN_KEY}"
        }
    """.trimIndent()

        return OkHttpClient.Builder()
            .addInterceptor { chain ->
                val request = chain.request()
                val mediaType = "application/json; charset=utf-8".toMediaType()
                val newBody = authentication.toRequestBody(mediaType)
                val newRequest = request.newBuilder()
                    .post(newBody)
                    .build()
                chain.proceed(newRequest)
            }
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constant.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun providePaintingService(retrofit: Retrofit): PaintingService {
        return retrofit.create(PaintingService::class.java)
    }
} 