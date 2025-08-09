package com.dktech.baseandroidviewdktech.ui.home.screen

import android.view.Gravity
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.StringRes
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.dktech.baseandroidviewdktech.R
import com.dktech.baseandroidviewdktech.base.BaseFragment
import com.dktech.baseandroidviewdktech.databinding.FragmentLessonBinding
import com.dktech.baseandroidviewdktech.ui.home.MainViewModel
import com.dktech.baseandroidviewdktech.ui.home.adapter.LessonItemAdapter
import com.dktech.baseandroidviewdktech.ui.home.model.toLessonUIWrapper
import com.dktech.baseandroidviewdktech.ui.home.screen.HomeFragment
import com.dktech.baseandroidviewdktech.utils.helper.pxFromDp
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlin.getValue


enum class LevelType(
    val title: String,
    @StringRes val res: Int,
    val level: Int
) {
    LEVEL_1(
        "Level 1", R.string.level_1, 1
    ),
    LEVEL_2(
        "Level 2", R.string.level_2, 2
    ),
    LEVEL_3(
        "Level 3", R.string.level_3, 3
    )
}


@AndroidEntryPoint
class LessonFragment : BaseFragment<FragmentLessonBinding>() {
    override fun getViewBinding(): FragmentLessonBinding {
        return FragmentLessonBinding.inflate(layoutInflater)
    }

    private val viewModel by activityViewModels<MainViewModel>()

    private val mAdapter by lazy { LessonItemAdapter() }

    override fun initView() {
        binding.lessonList.apply {
            adapter = mAdapter
            layoutManager = androidx.recyclerview.widget.LinearLayoutManager(context)
        }
        for (category in LevelType.entries) {
            binding.ctnLessons.addView(
                TextView(
                    this.requireContext()
                ).apply {
                    layoutParams = LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                    ).apply {
                        marginEnd = this@LessonFragment.requireContext().pxFromDp(12f).toInt()
                        gravity = Gravity.CENTER
                    }
                    id = category.ordinal
                    text = getString(category.res)
                    gravity = Gravity.CENTER
                    height = this@LessonFragment.requireContext().pxFromDp(32f).toInt()
                    setPadding(
                        this@LessonFragment.requireContext().pxFromDp(16f).toInt(),
                        0,
                        this@LessonFragment.requireContext().pxFromDp(16f).toInt(),
                        0
                    )
                    textSize = 14f
                    setTextColor(resources.getColor(R.color.onPrimary, null))
                    background = resources.getDrawable(R.drawable.bg_item_tab, null)
                    setOnClickListener {
                        viewModel.setCurrentSelectedLesson(category)
                    }
                },
            )
        }
        viewModel.setCurrentSelectedLesson(LevelType.LEVEL_1)
    }

    override fun initData() {
    }

    override fun initEvent() {
    }

    override fun observeData() {
        viewModel.currentSelectedLesson.onEach {
            updateCategoryView(it)
        }.launchIn(viewLifecycleOwner.lifecycleScope)

        viewModel.lessonList.onEach {
            mAdapter.updateDataList(it.map { it.toLessonUIWrapper() })
        }.launchIn(viewLifecycleOwner.lifecycleScope)
    }

    private fun updateCategoryView(selectedCategory: LevelType) {
        for (category in LevelType.entries) {
            val textView = binding.ctnLessons.findViewById<TextView>(category.ordinal)
            textView?.let{
                textView.isSelected = category == selectedCategory
            }
        }
    }
}