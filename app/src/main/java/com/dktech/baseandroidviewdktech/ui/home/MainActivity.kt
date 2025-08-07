package com.dktech.baseandroidviewdktech.ui.home

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.os.Bundle
import android.util.TypedValue
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


    private val expandedWidth by lazy {
        TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 120f, resources.displayMetrics).toInt()
    }
    private val collapsedWidth by lazy {
        TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 56f, resources.displayMetrics).toInt()
    }
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
        homeBinding.root.setOnClickListener {
            onItemBottomBarClick(homeBinding)
        }
        templateBinding.root.setOnClickListener {

            onItemBottomBarClick(templateBinding)
        }
        lessonBinding.root.setOnClickListener {

            onItemBottomBarClick(lessonBinding)
        }
        artworkBinding.root.setOnClickListener {

            onItemBottomBarClick(artworkBinding)
        }
    }

    private fun onItemBottomBarClick(itemBinding: ViewBinding) {
        homeBinding.apply {
            animateItem(root, false, expandedWidth, collapsedWidth, navLabel)
            root.background = null
        }
        templateBinding.apply {
            animateItem(root, false, expandedWidth, collapsedWidth, navLabel)
            root.background = null
        }
        lessonBinding.apply {
            animateItem(root, false, expandedWidth, collapsedWidth, navLabel)
            root.background = null
        }
        artworkBinding.apply {
            animateItem(root, false, expandedWidth, collapsedWidth, navLabel)
            root.background = null
        }

        when (itemBinding) {
            is NavItemHomeBinding -> {
                animateItem(itemBinding.root, true, collapsedWidth, expandedWidth, itemBinding.navLabel)
                itemBinding.root.background = ContextCompat.getDrawable(this, R.drawable.bg_selected_item)
            }
            is NavItemTemplateBinding -> {
                animateItem(itemBinding.root, true, collapsedWidth, expandedWidth, itemBinding.navLabel)
                itemBinding.root.background = ContextCompat.getDrawable(this, R.drawable.bg_selected_item)
            }
            is NavItemLessonBinding -> {
                animateItem(itemBinding.root, true, collapsedWidth, expandedWidth, itemBinding.navLabel)
                itemBinding.root.background = ContextCompat.getDrawable(this, R.drawable.bg_selected_item)
            }
            is NavItemArtworkBinding -> {
                animateItem(itemBinding.root, true, collapsedWidth, expandedWidth, itemBinding.navLabel)
                itemBinding.root.background = ContextCompat.getDrawable(this, R.drawable.bg_selected_item)
            }
        }

    }

    private fun animateItem(
        view: View,
        expand: Boolean,
        startWidth: Int,
        endWidth: Int,
        label: View
    ) {
        val widthAnimator = ValueAnimator.ofInt(startWidth, endWidth)
        widthAnimator.addUpdateListener { animator ->
            val params = view.layoutParams
            params.width = animator.animatedValue as Int
            view.layoutParams = params
        }

        // Animate label fade
        val alphaAnimator = ObjectAnimator.ofFloat(label, View.ALPHA, if (expand) 0f else 1f, if (expand) 1f else 0f)
        alphaAnimator.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationStart(animation: Animator) {
                if (expand) label.visibility = View.VISIBLE
            }

            override fun onAnimationEnd(animation: Animator) {
                if (!expand) label.visibility = View.GONE
            }
        })

        widthAnimator.duration = 200
        alphaAnimator.duration = 200

        widthAnimator.start()
        alphaAnimator.start()
    }

}