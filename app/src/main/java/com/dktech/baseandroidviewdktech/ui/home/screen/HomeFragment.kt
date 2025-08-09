package com.dktech.baseandroidviewdktech.ui.home.screen

import android.content.Intent
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.dktech.baseandroidviewdktech.base.BaseFragment
import com.dktech.baseandroidviewdktech.databinding.FragmentHomeBinding
import com.dktech.baseandroidviewdktech.ui.home.MainViewModel
import com.dktech.baseandroidviewdktech.ui.home.adapter.HomeTemplateAdapter
import com.dktech.baseandroidviewdktech.ui.how_to_use_screen.HowToUseActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>() {
    override fun getViewBinding(): FragmentHomeBinding {
        return FragmentHomeBinding.inflate(layoutInflater)
    }

    private val viewModel by activityViewModels<MainViewModel>()


    private val mTemplateAdapter by lazy {
        HomeTemplateAdapter()
    }


    override fun initView() {
        binding.templatesRecycler.apply {
            adapter = mTemplateAdapter
            layoutManager =
                androidx.recyclerview.widget.GridLayoutManager(
                    this@HomeFragment.requireActivity(),
                    3
                )
        }
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

    override fun observeData() {
        viewModel.dataList.onEach {
            mTemplateAdapter.updateDataList(it.map { it.thumpUrl ?: "" }.take(9))
        }.launchIn(lifecycleScope)
    }
}