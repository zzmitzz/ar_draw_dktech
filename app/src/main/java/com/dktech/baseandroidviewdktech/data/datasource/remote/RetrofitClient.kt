package com.dktech.baseandroidviewdktech.data.datasource.remote

import com.dktech.baseandroidviewdktech.utils.Constant
import com.dktech.baseandroidviewdktech.utils.Constant.BASE_URL
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {


    val authentication = """
    {
        "sign": "${Constant.SIGN_KEY}"
    }
""".trimIndent()


    val client = OkHttpClient.Builder()
        .addInterceptor { chain ->
            val request = chain.request()

            val mediaType = "application/json; charset=utf-8".toMediaType()
            val newBody = authentication.toRequestBody(mediaType)

            val newRequest = request.newBuilder()
                .post(newBody) // or .put(newBody) if method == PUT
                .build()

            return@addInterceptor chain.proceed(newRequest)
        }
        .build()


    val paintingServiceRemote: PaintingService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PaintingService::class.java)
    }
}