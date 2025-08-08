package com.dktech.baseandroidviewdktech.ui.home.screen

import androidx.fragment.app.activityViewModels
import com.dktech.baseandroidviewdktech.base.BaseFragment
import com.dktech.baseandroidviewdktech.databinding.FragmentLessonBinding
import com.dktech.baseandroidviewdktech.ui.home.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlin.getValue

@AndroidEntryPoint
class LessonFragment: BaseFragment<FragmentLessonBinding>() {
    override fun getViewBinding(): FragmentLessonBinding {
        return FragmentLessonBinding.inflate(layoutInflater)
    }

    private val viewModel by activityViewModels<MainViewModel>()
    override fun initView() {
    }

    override fun initData() {
    }

    override fun initEvent() {
    }
}