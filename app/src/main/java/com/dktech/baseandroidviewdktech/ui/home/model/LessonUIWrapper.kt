package com.dktech.baseandroidviewdktech.ui.home.model

import com.dktech.baseandroidviewdktech.data.model.PaintingDrawDTO

data class LessonUIWrapper(
    val id: String,
    val thumbImage: String,
    val imageStepList: List<String>,
    val step: Int,
    val level: Int,
)

fun PaintingDrawDTO.toLessonUIWrapper() = LessonUIWrapper(
    id = id.toString(),
    thumbImage = imageUrl.last(),
    imageStepList = imageUrl,
    step = step ?: 0,
    level = when(category){
        "Level 1" -> 1
        "Level 2" -> 2
        "Level 3" -> 3
        else -> 0
    },
)