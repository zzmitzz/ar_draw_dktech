package com.dktech.baseandroidviewdktech.data.repository

import com.dktech.baseandroidviewdktech.data.model.PaintingDrawDTO

interface PaintingDrawRepository {

    suspend fun getAllPaintingDraw(): List<PaintingDrawDTO>
    suspend fun getPaintingDrawByCategory(category: String): List<PaintingDrawDTO>
    suspend fun getPaintingDrawByID(id: String): PaintingDrawDTO

    suspend fun savePaintingDraw(paintingDrawDTO: PaintingDrawDTO): Boolean
    suspend fun savePaintingDraws(paintingDrawDTO: List<PaintingDrawDTO>): Boolean
}