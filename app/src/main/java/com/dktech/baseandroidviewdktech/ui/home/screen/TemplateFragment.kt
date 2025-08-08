package com.dktech.baseandroidviewdktech.ui.home.screen

import android.content.Intent
import androidx.fragment.app.activityViewModels
import com.dktech.baseandroidviewdktech.base.BaseFragment
import com.dktech.baseandroidviewdktech.databinding.FragmentTemplateBinding
import com.dktech.baseandroidviewdktech.ui.home.MainViewModel
import com.dktech.baseandroidviewdktech.ui.how_to_use_screen.HowToUseActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlin.getValue

@AndroidEntryPoint
class TemplateFragment : BaseFragment<FragmentTemplateBinding>() {
    override fun getViewBinding(): FragmentTemplateBinding {
        return FragmentTemplateBinding.inflate(layoutInflater)
    }

    private val viewModel by activityViewModels<MainViewModel>()
    override fun initView() {
    }

    override fun initData() {
    }

    override fun initEvent() {
        binding.searchIcon.setOnClickListener {
            Intent(this.requireContext(), HowToUseActivity::class.java).apply {
                startActivity(this)
            }
        }
    }
}