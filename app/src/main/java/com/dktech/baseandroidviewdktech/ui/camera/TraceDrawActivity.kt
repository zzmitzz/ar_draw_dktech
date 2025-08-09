package com.dktech.baseandroidviewdktech.ui.camera

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.hardware.camera2.CameraCharacteristics
import android.hardware.camera2.CameraManager
import android.os.Build
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.activity.viewModels
import androidx.camera.core.Camera
import androidx.camera.core.CameraProvider
import androidx.camera.core.CameraSelector
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import com.dktech.baseandroidviewdktech.R
import com.dktech.baseandroidviewdktech.base.BaseActivity
import com.dktech.baseandroidviewdktech.databinding.ActivityTraceDrawingBinding
import com.dktech.baseandroidviewdktech.utils.helper.gone
import com.dktech.baseandroidviewdktech.utils.helper.invisible
import com.dktech.baseandroidviewdktech.utils.helper.setSafeOnClickListener
import com.dktech.baseandroidviewdktech.utils.helper.visible
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class TraceDrawActivity : BaseActivity<ActivityTraceDrawingBinding>() {


    private val viewModel: TraceDrawVM by viewModels<TraceDrawVM>()
    override val onBackPressedCallback: OnBackPressedCallback
        get() = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                finish()
            }
        }
    private lateinit var camera: Camera
    override fun getViewBinding(): ActivityTraceDrawingBinding {
        return ActivityTraceDrawingBinding.inflate(layoutInflater)
    }

    override fun initData() {
    }

    override fun initView() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
            == PackageManager.PERMISSION_GRANTED) {
            startCamera()
        } else{
            Toast.makeText(this, "Permission not granted", Toast.LENGTH_SHORT).show()
        }
    }

    override fun initEvent() {
        binding.btnBack.setSafeOnClickListener {
            finish()
        }
        binding.btnOpacity.setOnClickListener {
            viewModel.switchOpacity()
        }
        binding.btnFlash.setOnClickListener {
            viewModel.switchFlash()
        }
        binding.btnFlip.setOnClickListener {
            viewModel.switchFlip()
        }
        binding.btnLock.setOnClickListener {
            viewModel.switchLock()
        }

    }

    override fun initObserver() {
        viewModel.configSetting.onEach {
            setOpacity(it.opacityVisible)
            setFlash(it.flash)
            setFlip(it.flip)
            setLock(it.lock)
        }.launchIn(lifecycleScope)
    }

    private fun setOpacity(isOpacity: Boolean){
        binding.btnOpacity.isSelected = isOpacity
        binding.btnOpacity.background = if (isOpacity) getDrawable(R.drawable.bg_selected_item) else null
        if(isOpacity){
            binding.opacityLayout.visible()
        }else{
            binding.opacityLayout.gone()
        }
    }

    private fun setFlip(isFlip: Boolean) {
        binding.btnFlip.isSelected = isFlip
        binding.btnFlip.background = if (isFlip) getDrawable(R.drawable.bg_selected_item) else null
    }

    private fun setFlash(isFlash: Boolean) {
        binding.btnFlash.isSelected = isFlash
        if(::camera.isInitialized){
            camera.cameraControl.enableTorch(isFlash)
        }
        binding.btnFlash.background = if (isFlash) getDrawable(R.drawable.bg_selected_item) else null
    }

    private fun setLock(isLock: Boolean) {
        binding.btnLock.isSelected = isLock
        binding.btnLock.background = if (isLock) getDrawable(R.drawable.bg_selected_item) else null
    }

    private fun startCamera(){
        val cameraProviderFuture = ProcessCameraProvider.getInstance(this)

        cameraProviderFuture.addListener({
            val cameraProvider = cameraProviderFuture.get()

            val preview = Preview.Builder().build().also {
                it.surfaceProvider = binding.previewView.surfaceProvider
            }

            val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA

            try {
                cameraProvider.unbindAll()
                camera = cameraProvider.bindToLifecycle(
                    this, cameraSelector, preview
                )
            } catch (exc: Exception) {
            }
        }, ContextCompat.getMainExecutor(this))
    }

    companion object {
        const val TAG = "TraceDrawActivity"
        const val LIST_IMAGE_DATA = "listImageData"
    }

    override fun onDestroy() {
        super.onDestroy()

    }
}