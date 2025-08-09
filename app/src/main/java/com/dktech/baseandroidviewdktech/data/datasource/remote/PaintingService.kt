package com.dktech.baseandroidviewdktech.data.datasource.remote

import com.dktech.baseandroidviewdktech.data.model.PaintingDrawDTO
import kotlinx.serialization.Serializable
import kotlinx.serialization.Serializer
import retrofit2.http.GET
import retrofit2.http.Query


@Serializable
data class ResponseData<T>(
    val status: String,
    val message: String,
    val data: T
)


interface PaintingService {
    @GET("ardraw/com.ardrawing.sketch.trace.paint.draw")
    suspend fun getItemDrawData(
    ): ResponseData<List<PaintingDrawDTO>>
}