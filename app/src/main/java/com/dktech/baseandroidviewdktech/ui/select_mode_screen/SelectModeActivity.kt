package com.dktech.baseandroidviewdktech.ui.select_mode_screen

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import com.dktech.baseandroidviewdktech.base.BaseActivity
import com.dktech.baseandroidviewdktech.databinding.ActivitySelectModeBinding
import com.dktech.baseandroidviewdktech.ui.camera.TraceDrawActivity
import com.dktech.baseandroidviewdktech.utils.helper.setSafeOnClickListener
import dagger.hilt.android.AndroidEntryPoint


enum class ModeSelected(
    val id: Int
){
    TRACE(99),
    SKETCH(100)
}

@AndroidEntryPoint
class SelectModeActivity : BaseActivity<ActivitySelectModeBinding>() {
    private var modeSelected: ModeSelected = ModeSelected.TRACE


    private val intentPermission = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ){ isGranted ->
        if (isGranted) {
            nextActivity()
        } else {
            Toast.makeText(this, "Permission needed for this feature", Toast.LENGTH_SHORT).show()
        }
    }

    override val onBackPressedCallback: OnBackPressedCallback
        get() = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                finish()
            }
        }


    override fun getViewBinding(): ActivitySelectModeBinding {
        return ActivitySelectModeBinding.inflate(layoutInflater)
    }

    override fun initData() {
    }

    override fun initView() {
        updateModeSelected(modeSelected)
    }

    override fun initEvent() {
        binding.traceDrawing.setSafeOnClickListener {
            if(modeSelected == ModeSelected.TRACE) return@setSafeOnClickListener
            modeSelected = ModeSelected.TRACE
            updateModeSelected(modeSelected)
        }
        binding.sketchDrawing.setSafeOnClickListener {
            if(modeSelected == ModeSelected.SKETCH) return@setSafeOnClickListener
            modeSelected = ModeSelected.SKETCH
            updateModeSelected(modeSelected)
        }
        binding.ivDone.setSafeOnClickListener {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                == PackageManager.PERMISSION_GRANTED
            ) {
                nextActivity()
            } else {
                intentPermission.launch(Manifest.permission.CAMERA)
            }
        }
    }

    override fun initObserver() {

    }

    private fun nextActivity() {
        val intentData = Intent()
        intentData.putExtra("mode", modeSelected.id)
        setResult(Activity.RESULT_OK, intentData )
        finish()
    }

    private fun updateModeSelected(modeSelected: ModeSelected) {
        if(modeSelected == ModeSelected.TRACE) {
            binding.traceDrawing.isSelected = true
            binding.sketchDrawing.isSelected = false
        }else{
            binding.traceDrawing.isSelected = false
            binding.sketchDrawing.isSelected = true
        }
    }

}