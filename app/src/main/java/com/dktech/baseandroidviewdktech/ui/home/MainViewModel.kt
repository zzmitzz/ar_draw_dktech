package com.dktech.baseandroidviewdktech.ui.home

import com.dktech.baseandroidviewdktech.base.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class MainViewModel: BaseViewModel() {
    private val _currentFragment = MutableStateFlow<TabFragment>(TabFragment.HOME)
    val currentFragment = _currentFragment.asStateFlow()

    fun setCurrentFragment(fragment: TabFragment) {
        _currentFragment.value = fragment
    }
}