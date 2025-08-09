package com.dktech.baseandroidviewdktech.ui.home

import android.content.Context
import android.graphics.Bitmap
import androidx.lifecycle.viewModelScope
import com.dktech.baseandroidviewdktech.base.BaseViewModel
import com.dktech.baseandroidviewdktech.data.model.PaintingDrawDTO
import com.dktech.baseandroidviewdktech.data.repository.PaintingDrawRepository
import com.dktech.baseandroidviewdktech.ui.home.screen.LevelType
import com.dktech.baseandroidviewdktech.ui.home.screen.TemplateCategory
import com.dktech.baseandroidviewdktech.utils.Constant
import com.dktech.baseandroidviewdktech.utils.helper.getImageFromCache
import com.dktech.baseandroidviewdktech.utils.helper.saveImageToCache
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val paintingDrawRepository: PaintingDrawRepository
) : BaseViewModel() {
    private val _currentFragment = MutableStateFlow<TabFragment>(TabFragment.HOME)
    val currentFragment = _currentFragment.asStateFlow()
    fun setCurrentFragment(fragment: TabFragment) {
        _currentFragment.value = fragment
    }


    private val _currentSelectedCategory = MutableStateFlow<TemplateCategory>(TemplateCategory.ALL)

    val currentSelectedCategory = _currentSelectedCategory.asStateFlow()

    fun setCurrentSelectedCategory(category: TemplateCategory) {
        _currentSelectedCategory.value = category
        if (category != TemplateCategory.ALL) {
            getDataPaintingDrawByCategory(category.name)
        } else {
            getDataPaintingDraw()
        }
    }


    var lessonList = MutableStateFlow(listOf<PaintingDrawDTO>())
    private val _currentSelectedLesson = MutableStateFlow<LevelType>(LevelType.LEVEL_1)
    val currentSelectedLesson = _currentSelectedLesson.asStateFlow()
    fun setCurrentSelectedLesson(category: LevelType) {
        _currentSelectedLesson.value = category
        getDataPaintingDrawByLesson(category.title)
    }


    private val _dataList = MutableStateFlow(listOf<PaintingDrawDTO>())
    val dataList = _dataList.asStateFlow()
    val rawListData = mutableListOf<PaintingDrawDTO>()

    fun getDataPaintingDraw() {
        viewModelScope.launch {
            setLoadingDialog(true)
            _dataList.value = paintingDrawRepository.getAllPaintingDraw()
            rawListData.addAll(_dataList.value)
            setLoadingDialog(false)
        }
    }

    fun getDataPaintingDrawByCategory(category: String) {
        viewModelScope.launch {
            setLoadingDialog(true)
            _dataList.value = paintingDrawRepository.getPaintingDrawByCategory(category)
            setLoadingDialog(false)
        }
    }

    fun getDataPaintingDrawByLesson(level: String) {
        viewModelScope.launch {
            setLoadingDialog(true)
            lessonList.value = paintingDrawRepository.getPaintingDrawByCategory(level)
            setLoadingDialog(false)
        }
    }


    fun saveImagesToCache(
        context: Context,
        paintingDrawDTO: PaintingDrawDTO,
        callback: (List<String>) -> Unit
    ): Unit {
        viewModelScope.launch {
            setLoadingDialog(true)
            paintingDrawDTO.imageUrl.map {
                async {
                    val bitmap: Bitmap? = context.getImageFromCache((it + Constant.EXTENSION))
                    if (bitmap == null) {
                        context.saveImageToCache(it, (it + Constant.EXTENSION))
                    }
                }
            }.awaitAll()
            setLoadingDialog(false)
            callback.invoke(paintingDrawDTO.imageUrl)
        }
    }
}