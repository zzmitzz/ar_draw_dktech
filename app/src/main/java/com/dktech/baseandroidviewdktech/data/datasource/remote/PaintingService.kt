package com.dktech.baseandroidviewdktech.data.datasource.remote

import com.dktech.baseandroidviewdktech.data.model.PaintingDrawDTO
import retrofit2.http.GET
import retrofit2.http.Query

interface PaintingService {
    @GET("ardraw/com.ardrawing.sketch.trace.paint.draw")
    suspend fun getItemDrawData(
        @Query("sign") sign: String
    ): PaintingDrawDTO
}