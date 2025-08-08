package com.dktech.baseandroidviewdktech.ui.home

import com.dktech.baseandroidviewdktech.base.BaseViewModel
import com.dktech.baseandroidviewdktech.data.repository.PaintingDrawRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val paintingDrawRepository: PaintingDrawRepository
): BaseViewModel() {
    private val _currentFragment = MutableStateFlow<TabFragment>(TabFragment.HOME)
    val currentFragment = _currentFragment.asStateFlow()

    fun setCurrentFragment(fragment: TabFragment) {
        _currentFragment.value = fragment
    }
}