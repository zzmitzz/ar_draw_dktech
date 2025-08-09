package com.dktech.baseandroidviewdktech.ui.home

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.os.Bundle
import android.util.TypedValue
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.viewbinding.ViewBinding
import com.dktech.baseandroidviewdktech.R
import com.dktech.baseandroidviewdktech.base.BaseActivity
import com.dktech.baseandroidviewdktech.base.dialog.LoadingDialog
import com.dktech.baseandroidviewdktech.databinding.ActivityMainBinding
import com.dktech.baseandroidviewdktech.databinding.NavItemArtworkBinding
import com.dktech.baseandroidviewdktech.databinding.NavItemHomeBinding
import com.dktech.baseandroidviewdktech.databinding.NavItemLessonBinding
import com.dktech.baseandroidviewdktech.databinding.NavItemTemplateBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach


enum class TabFragment(
    val id: Int,
    val title: String,
    val navId: Int
) {
    HOME(0, "Home", R.id.homeFragment),
    TEMPLATE(1, "Template", R.id.templateFragment),
    LESSON(2, "Lesson", R.id.lessonFragment),
    ARTWORK(3, "ARTWORK", R.id.artworkFragment)
}


@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {

    private lateinit var navController: NavController

    private val expandedWidth by lazy {
        TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 120f, resources.displayMetrics)
            .toInt()
    }
    private val collapsedWidth by lazy {
        TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 56f, resources.displayMetrics)
            .toInt()
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

    private val loadingDialog by lazy {
        LoadingDialog(this)
    }

    private val viewModel by viewModels<MainViewModel>()
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

    override fun getViewBinding(): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }

    override fun initData() {
        viewModel.getDataPaintingDraw()
    }

    override fun initView() {
        setupNavigation()
        setupBottomBar()
        viewModel.setCurrentFragment(TabFragment.HOME)
    }

    override fun initEvent() {

    }

    override fun initObserver() {
        viewModel.currentFragment.onEach {
            when (it) {
                TabFragment.HOME -> onItemBottomBarClick(homeBinding)
                TabFragment.TEMPLATE -> onItemBottomBarClick(templateBinding)
                TabFragment.LESSON -> onItemBottomBarClick(lessonBinding)
                TabFragment.ARTWORK -> onItemBottomBarClick(artworkBinding)
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

    private fun setupNavigation() {
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
    }

    private fun setupBottomBar() {
        homeBinding.root.setOnClickListener {
            if (viewModel.currentFragment.value == TabFragment.HOME) return@setOnClickListener
            viewModel.setCurrentFragment(TabFragment.HOME)
            navController.navigate(TabFragment.HOME.navId)
        }
        templateBinding.root.setOnClickListener {
            if (viewModel.currentFragment.value == TabFragment.TEMPLATE) return@setOnClickListener
            viewModel.setCurrentFragment(TabFragment.TEMPLATE)
            navController.navigate(TabFragment.TEMPLATE.navId)
        }
        lessonBinding.root.setOnClickListener {
            if (viewModel.currentFragment.value == TabFragment.LESSON) return@setOnClickListener
            viewModel.setCurrentFragment(TabFragment.LESSON)
            navController.navigate(TabFragment.LESSON.navId)
        }
        artworkBinding.root.setOnClickListener {
            if (viewModel.currentFragment.value == TabFragment.ARTWORK) return@setOnClickListener
            viewModel.setCurrentFragment(TabFragment.ARTWORK)
            navController.navigate(TabFragment.ARTWORK.navId)
        }
    }

    private fun onItemBottomBarClick(itemBinding: ViewBinding) {
        homeBinding.apply {
            navLabel.visibility = View.GONE
            animateItem(root, false, expandedWidth, collapsedWidth, navLabel)
            root.background = null
        }
        templateBinding.apply {
            navLabel.visibility = View.GONE
            animateItem(root, false, expandedWidth, collapsedWidth, navLabel)
            root.background = null
        }
        lessonBinding.apply {
            navLabel.visibility = View.GONE
            animateItem(root, false, expandedWidth, collapsedWidth, navLabel)
            root.background = null
        }
        artworkBinding.apply {
            navLabel.visibility = View.GONE
            animateItem(root, false, expandedWidth, collapsedWidth, navLabel)
            root.background = null
        }

        when (itemBinding) {
            is NavItemHomeBinding -> {
                animateItem(
                    itemBinding.root,
                    true,
                    collapsedWidth,
                    expandedWidth,
                    itemBinding.navLabel
                )
                itemBinding.root.background =
                    ContextCompat.getDrawable(this, R.drawable.bg_selected_item)
            }

            is NavItemTemplateBinding -> {
                animateItem(
                    itemBinding.root,
                    true,
                    collapsedWidth,
                    expandedWidth,
                    itemBinding.navLabel
                )
                itemBinding.root.background =
                    ContextCompat.getDrawable(this, R.drawable.bg_selected_item)
            }

            is NavItemLessonBinding -> {
                animateItem(
                    itemBinding.root,
                    true,
                    collapsedWidth,
                    expandedWidth,
                    itemBinding.navLabel
                )
                itemBinding.root.background =
                    ContextCompat.getDrawable(this, R.drawable.bg_selected_item)
            }

            is NavItemArtworkBinding -> {
                animateItem(
                    itemBinding.root,
                    true,
                    collapsedWidth,
                    expandedWidth,
                    itemBinding.navLabel
                )
                itemBinding.root.background =
                    ContextCompat.getDrawable(this, R.drawable.bg_selected_item)
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
        val alphaAnimator = ObjectAnimator.ofFloat(
            label,
            View.ALPHA,
            if (expand) 0f else 1f,
            if (expand) 1f else 0f
        )
        alphaAnimator.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationStart(animation: Animator) {
                if (expand) label.visibility = View.VISIBLE
            }

            override fun onAnimationEnd(animation: Animator) {
//                if (!expand) label.visibility = View.GONE
            }
        })

        widthAnimator.duration = 200
        alphaAnimator.duration = 200

        widthAnimator.start()
        alphaAnimator.start()
    }


}