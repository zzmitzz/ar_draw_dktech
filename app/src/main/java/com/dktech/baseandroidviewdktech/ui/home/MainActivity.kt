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
import com.dktech.baseandroidviewdktech.MainViewModel
import com.dktech.baseandroidviewdktech.R
import com.dktech.baseandroidviewdktech.base.BaseActivityVM
import com.dktech.baseandroidviewdktech.base.ViewModelFactory
import com.dktech.baseandroidviewdktech.databinding.ActivityMainBinding
import com.dktech.baseandroidviewdktech.databinding.NavItemHomeBinding
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
    }

    override fun initEvent() {

    }

    override fun initObserver() {

    }
    fun setupBottomBar() {
        val homeBinding = NavItemHomeBinding.bind(binding.navHome.root)
        val templateBinding = NavItemTemplateBinding.bind(binding.navTemplate.root)
        val lessonBinding = NavItemHomeBinding.bind(binding.navLesson.root)
        val artworkBinding = NavItemHomeBinding.bind(binding.navArtwork.root)
        val tabs = listOf(
            homeBinding to TabFragment.HOME,
            templateBinding to TabFragment.TEMPLATE,
            lessonBinding to TabFragment.LESSON,
            artworkBinding to TabFragment.ARTWORK
        )

        tabs.forEach { (id, _) ->
            val tabView = findViewById<LinearLayout>(id.root.id)
            val icon = tabView.findViewById<ImageView>(R.id.navIcon)
            val label = tabView.findViewById<TextView>(R.id.navLabel)

            tabView.setOnClickListener {
                // Reset all tabs
                tabs.forEach { (resetId, _) ->
                    val view = findViewById<LinearLayout>(resetId)
                    val viewLabel = view.findViewById<TextView>(R.id.navLabel)
                    viewLabel.visibility = View.GONE
                    view.background = null
                }

                // Set selected tab
                label.visibility = View.VISIBLE
                tabView.background = ContextCompat.getDrawable(this, R.drawable.bg_selected_item)
            }
        }

        homeBinding.root.visibility = View.VISIBLE
        findViewById<LinearLayout>(R.id.navHome).background = ContextCompat.getDrawable(this, R.drawable.bg_selected_item)
    }
}