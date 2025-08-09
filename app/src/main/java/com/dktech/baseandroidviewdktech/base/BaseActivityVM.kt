package com.dktech.baseandroidviewdktech.base

import android.os.Bundle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.viewbinding.ViewBinding
import com.dktech.baseandroidviewdktech.base.dialog.LoadingDialog
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import java.lang.reflect.ParameterizedType
import javax.inject.Inject


//abstract class BaseActivityVM<VM : BaseViewModel, VB : ViewBinding> : BaseActivity<VB>() {
//    protected lateinit var viewModel: VM
//
//
//    abstract val viewModelClass: Class<VM>
//
//
//
//    private val loadingDialog by lazy {
//        LoadingDialog(this)
//    }
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        initViewModel()
//        initLoadingDialog()
//        initObserver()
//    }
//
//    open fun initViewModel(){
//        viewModel = ViewModelProvider(this, viewModelFactory).get(viewModelClass)
//    }
//    private fun initLoadingDialog(){
//        viewModel.loadingDialog.onEach {
//            if (it) {
//                loadingDialog.show()
//            } else {
//                loadingDialog.hide()
//            }
//        }.launchIn(lifecycleScope)
//    }
//}