package com.dktech.baseandroidviewdktech.ui.how_to_use_screen

import android.graphics.Typeface
import androidx.activity.OnBackPressedCallback
import com.dktech.baseandroidviewdktech.R
import com.dktech.baseandroidviewdktech.base.BaseActivity
import com.dktech.baseandroidviewdktech.databinding.ActivityHowToUseBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HowToUseActivity : BaseActivity<ActivityHowToUseBinding>() {
    override val onBackPressedCallback: OnBackPressedCallback
        get() = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                finish()
            }
        }

    override fun getViewBinding(): ActivityHowToUseBinding {
        return ActivityHowToUseBinding.inflate(layoutInflater)
    }

    override fun initData() {

    }

    override fun initView() {
        updateUI(0)
        binding.icBack.setOnClickListener {
            finish()
        }
    }

    override fun initEvent() {
        binding.tvTrace.setOnClickListener {
            updateUI(0)
        }
        binding.tvSketch.setOnClickListener {
            updateUI(1)
        }
    }

    private fun updateUI(type: Int){
        when(type){
            0 -> {
                binding.apply {
                    tvTitle.text = getString(R.string.trace_drawing)
                    tvDescription.text = getString(R.string.secure_your_phone_using_a_tripod_a_glass_or_even_a_stack_of_books_to_keep_it_steady_while_you_draw)
                    tvTrace.isSelected = true
                    tvSketch.isSelected = false
                    tvTrace.setTypeface(null, Typeface.BOLD)
                }
            }
            1 -> {
                binding.apply {
                    tvTitle.text = getString(R.string.sketch_drawing)
                    tvDescription.text = getString(R.string.place_a_sheet)
                    tvTrace.isSelected = false
                    tvSketch.isSelected = true
                    tvSketch.setTypeface(null, Typeface.BOLD)
                }
            }
        }
    }
    override fun initObserver() {
    }

}