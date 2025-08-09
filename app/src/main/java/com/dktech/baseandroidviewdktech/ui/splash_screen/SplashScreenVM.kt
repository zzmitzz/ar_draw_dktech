package com.dktech.baseandroidviewdktech.ui.splash_screen

import androidx.lifecycle.viewModelScope
import com.dktech.baseandroidviewdktech.base.BaseViewModel
import com.dktech.baseandroidviewdktech.data.SingleDataSource
import com.dktech.baseandroidviewdktech.data.repository.PaintingDrawRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SplashScreenVM @Inject constructor(
    private val paintingDrawRepository: PaintingDrawRepository
) : BaseViewModel() {

    private val _fetchJobState = MutableStateFlow(false)
    val fetchJobState get() = _fetchJobState
    fun fetchDataJob() {
        if (SingleDataSource.dataSource.isEmpty()) {
            viewModelScope.launch {
                paintingDrawRepository.getAllPaintingDraw()
                _fetchJobState.value = true
            }
        }
    }
}