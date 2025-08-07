package com.dktech.baseandroidviewdktech.ui.home

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.activity.enableEdgeToEdge
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.dktech.baseandroidviewdktech.MainViewModel
import com.dktech.baseandroidviewdktech.R
import com.dktech.baseandroidviewdktech.base.BaseActivityVM
import com.dktech.baseandroidviewdktech.base.ViewModelFactory
import com.dktech.baseandroidviewdktech.databinding.ActivityMainBinding
import com.dktech.baseandroidviewdktech.databinding.NavItemArtworkBinding
import com.dktech.baseandroidviewdktech.databinding.NavItemHomeBinding
import com.dktech.baseandroidviewdktech.databinding.NavItemLessonBinding
import com.dktech.baseandroidviewdktech.databinding.NavItemTemplateBinding


enum class TabFragment(
    val id: Int,
    val title: String
){
    HOME(0, "Home"),
    TEMPLATE(1, "Template"),
    LESSON(2, "Lesson"),
    ARTWORK(3, "ARTWORK")
}


class MainActivity : BaseActivityVM<MainViewModel, ActivityMainBinding>() {


    private val homeBinding by lazy {
        NavItemHomeBinding.bind(binding.navHome.root)
    }
    private val templateBinding by lazy {
        NavItemTemplateBinding.bind(binding.navTemplate.root)
    }
    private val lessonBinding by lazy {
        NavItemLessonBinding.bind(binding.navLesson.root)
    }
    private val artworkBinding by lazy {
        NavItemArtworkBinding.bind(binding.navArtwork.root)
    }

    override val onBackPressedCallback: OnBackPressedCallback
        get() = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                finish()
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }

    override fun initViewModel() {
        viewModel = ViewModelProvider(this, ViewModelFactory())[MainViewModel::class.java]
    }

    override fun getViewBinding(): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }

    override fun initData() {

    }

    override fun initView() {
        setupBottomBar()
        homeBinding.root.performClick()
    }

    override fun initEvent() {

    }

    override fun initObserver() {

    }
    private fun setupBottomBar() {
        homeBinding.apply {
            onItemBottomBarClick(this)
        }
        templateBinding.apply {
            onItemBottomBarClick(this)
        }
        lessonBinding.apply {
            onItemBottomBarClick(this)
        }
        artworkBinding.apply {
            onItemBottomBarClick(this)
        }
    }

    private fun onItemBottomBarClick(itemBinding: ViewBinding) {
        homeBinding.apply {
            this.navLabel.visibility = View.GONE
            this.root.background = null
        }
        templateBinding.apply {
            this.navLabel.visibility = View.GONE
            this.root.background = null
        }
        lessonBinding.apply {
            this.navLabel.visibility = View.GONE
            this.root.background = null
        }
        artworkBinding.apply {
            this.navLabel.visibility = View.GONE
            this.root.background = null
        }
        when (itemBinding) {
            is NavItemHomeBinding -> {
                itemBinding.navLabel.visibility = View.VISIBLE
                itemBinding.root.background = ContextCompat.getDrawable(this, R.drawable.bg_selected_item)
            }
            is NavItemTemplateBinding -> {
                itemBinding.navLabel.visibility = View.VISIBLE
                itemBinding.root.background = ContextCompat.getDrawable(this, R.drawable.bg_selected_item)
            }
            is NavItemLessonBinding -> {
                itemBinding.navLabel.visibility = View.VISIBLE
                itemBinding.root.background = ContextCompat.getDrawable(this, R.drawable.bg_selected_item)
            }
            is NavItemArtworkBinding -> {
                itemBinding.navLabel.visibility = View.VISIBLE
                itemBinding.root.background = ContextCompat.getDrawable(this, R.drawable.bg_selected_item)
            }
        }
    }

}