package com.dktech.baseandroidviewdktech.ui.splash_screen

import android.content.Intent
import android.os.Handler
import androidx.activity.OnBackPressedCallback
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.dktech.baseandroidviewdktech.base.BaseActivity
import com.dktech.baseandroidviewdktech.base.BaseViewModel
import com.dktech.baseandroidviewdktech.base.dialog.LoadingDialog
import com.dktech.baseandroidviewdktech.databinding.LayoutActivitySplashscreenBinding
import com.dktech.baseandroidviewdktech.ui.home.MainActivity
import com.dktech.baseandroidviewdktech.ui.language_screen.LanguageScreenActivity
import com.dktech.baseandroidviewdktech.utils.helper.NetworkUtils
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class SplashScreenActivity : BaseActivity<LayoutActivitySplashscreenBinding>() {
    override val onBackPressedCallback: OnBackPressedCallback
        get() = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
            }
        }
    private val viewModel by viewModels<SplashScreenVM>()

    private val loadingDialog by lazy {
        LoadingDialog(this)
    }

    override fun getViewBinding(): LayoutActivitySplashscreenBinding {
        return LayoutActivitySplashscreenBinding.inflate(layoutInflater)
    }

    override fun initData() {
        if (NetworkUtils.isNetworkConnected(this)) {
            viewModel.fetchDataJob()
        }
    }

    override fun initView() {

    }

    override fun initEvent() {
    }

    override fun initObserver() {
        viewModel.fetchJobState.onEach {
            if (it) {
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }
        }.launchIn(lifecycleScope)

        viewModel.loadingDialog.onEach {
            if (it) {
                loadingDialog.show()
            } else {
                loadingDialog.hide()
            }
        }.launchIn(lifecycleScope)
    }

}