package com.dktech.baseandroidviewdktech.ui.home.screen

import android.content.Intent
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.dktech.baseandroidviewdktech.base.BaseFragment
import com.dktech.baseandroidviewdktech.databinding.FragmentHomeBinding
import com.dktech.baseandroidviewdktech.ui.home.MainViewModel
import com.dktech.baseandroidviewdktech.ui.how_to_use_screen.HowToUseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>() {
    override fun getViewBinding(): FragmentHomeBinding {
        return FragmentHomeBinding.inflate(layoutInflater)
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