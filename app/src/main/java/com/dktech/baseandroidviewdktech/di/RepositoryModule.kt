package com.dktech.baseandroidviewdktech.di

import com.dktech.baseandroidviewdktech.data.repository.PaintingDrawRepository
import com.dktech.baseandroidviewdktech.data.repository.PaintingDrawRepositoryImplement
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {

    @Binds
    @ViewModelScoped
    abstract fun bindPaintingDrawRepository(
        paintingDrawRepositoryImplement: PaintingDrawRepositoryImplement
    ): PaintingDrawRepository
} 