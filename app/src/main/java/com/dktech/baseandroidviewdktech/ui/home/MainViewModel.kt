package com.dktech.baseandroidviewdktech.ui.home

import androidx.lifecycle.viewModelScope
import com.dktech.baseandroidviewdktech.base.BaseViewModel
import com.dktech.baseandroidviewdktech.data.model.PaintingDrawDTO
import com.dktech.baseandroidviewdktech.data.repository.PaintingDrawRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
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

    private val _dataList = MutableStateFlow(listOf<PaintingDrawDTO>())
    val dataList = _dataList.asStateFlow()

    fun getDataPaintingDraw() {
        viewModelScope.launch {
            setLoadingDialog(true)
            _dataList.value = paintingDrawRepository.getAllPaintingDraw()
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
}