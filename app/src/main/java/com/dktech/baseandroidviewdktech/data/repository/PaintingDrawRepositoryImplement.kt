package com.dktech.baseandroidviewdktech.data.repository

import com.dktech.baseandroidviewdktech.data.datasource.remote.PaintingService
import com.dktech.baseandroidviewdktech.data.model.PaintingDrawDTO
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class PaintingDrawRepositoryImplement @Inject constructor(
    private val paintingService: PaintingService
): PaintingDrawRepository {

    override suspend fun getAllPaintingDraw(): List<PaintingDrawDTO> {
        return listOf()
    }

    override suspend fun getPaintingDrawByCategory(category: String): List<PaintingDrawDTO> {
        return listOf()
    }

    override suspend fun getPaintingDrawByID(id: String): PaintingDrawDTO {
        return PaintingDrawDTO()
    }

    override suspend fun savePaintingDraw(paintingDrawDTO: PaintingDrawDTO): Boolean {
        return true
    }

    override suspend fun savePaintingDraws(paintingDrawDTO: List<PaintingDrawDTO>): Boolean {
        return true
    }
}