package com.dktech.baseandroidviewdktech.ui.home.screen

import android.app.Activity
import android.content.Intent
import android.view.Gravity
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.StringRes
import androidx.core.view.marginEnd
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.dktech.baseandroidviewdktech.R
import com.dktech.baseandroidviewdktech.base.BaseFragment
import com.dktech.baseandroidviewdktech.databinding.FragmentHomeBinding
import com.dktech.baseandroidviewdktech.ui.home.MainViewModel
import com.dktech.baseandroidviewdktech.ui.camera.TraceDrawActivity
import com.dktech.baseandroidviewdktech.ui.home.adapter.HomeLessonAdapter
import com.dktech.baseandroidviewdktech.ui.home.adapter.HomeTemplateAdapter
import com.dktech.baseandroidviewdktech.ui.how_to_use_screen.HowToUseActivity
import com.dktech.baseandroidviewdktech.ui.select_mode_screen.ModeSelected
import com.dktech.baseandroidviewdktech.ui.select_mode_screen.SelectModeActivity
import com.dktech.baseandroidviewdktech.utils.helper.dpFromPx
import com.dktech.baseandroidviewdktech.utils.helper.pxFromDp
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach


enum class TemplateCategory(val text: String, @StringRes val id: Int) {
    CUTE("cute", R.string.cute),
    ANIMAL("animal", R.string.animal),
    ANIME("anime", R.string.anime),
    ALL("all", R.string.all_template),
    TRENDING("trending", R.string.trending),
    CHIBI("chibi", R.string.chibi),
    SPORT("sport", R.string.sport),
    CARTOON("cartoon", R.string.cartoon),
}


@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>() {


    override fun getViewBinding(): FragmentHomeBinding {
        return FragmentHomeBinding.inflate(layoutInflater)
    }

    private val viewModel by activityViewModels<MainViewModel>()

    private lateinit var intentSelectMode : ActivityResultLauncher<Intent>


    private var listFiles = mutableListOf<String>()
    private val mTemplateAdapter by lazy {
        HomeTemplateAdapter() { url ->
            viewModel.saveImagesToCache(this.requireActivity(), url) { list ->
                listFiles.addAll(list)
                intentSelectMode.launch(
                    Intent(this.requireActivity(), SelectModeActivity::class.java)
                )
            }
        }
    }

    private val mLessonsAdapter by lazy {
        HomeLessonAdapter() { url ->
            viewModel.saveImagesToCache(this.requireActivity(), url) { list ->
                listFiles.addAll(list)
                intentSelectMode.launch(
                    Intent(this.requireActivity(), SelectModeActivity::class.java)
                )
            }
        }
    }

    override fun initView() {
        binding.rcvLessons.apply {
            adapter = mLessonsAdapter
            setHasFixedSize(true)
        }

        binding.templatesRecycler.apply {
            adapter = mTemplateAdapter
            layoutManager =
                androidx.recyclerview.widget.GridLayoutManager(
                    this@HomeFragment.requireActivity(),
                    3
                )
            setHasFixedSize(true)
        }
        for (category in TemplateCategory.entries) {
            binding.ctnCategories.addView(
                TextView(
                    this.requireContext()
                ).apply {
                    layoutParams = LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                    ).apply {
                        marginEnd = this@HomeFragment.requireContext().pxFromDp(12f).toInt()
                        gravity = Gravity.CENTER
                    }
                    id = category.ordinal
                    text = getString(category.id)
                    gravity = Gravity.CENTER
                    height = this@HomeFragment.requireContext().pxFromDp(32f).toInt()
                    setPadding(
                        this@HomeFragment.requireContext().pxFromDp(16f).toInt(),
                        0,
                        this@HomeFragment.requireContext().pxFromDp(16f).toInt(),
                        0
                    )
                    textSize = 14f
                    setTextColor(resources.getColor(R.color.onPrimary, null))
                    background = resources.getDrawable(R.drawable.bg_item_tab, null)
                    setOnClickListener {
                        viewModel.setCurrentSelectedCategory(category)
                    }
                },
            )
        }

        intentSelectMode = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val data = result.data?.getIntExtra("mode", 99)
                if(data == ModeSelected.TRACE.id){
                    if(listFiles.isNotEmpty()) {
                        Intent(this.requireContext(), TraceDrawActivity::class.java).apply {
                            putStringArrayListExtra(TraceDrawActivity.LIST_IMAGE_DATA, listFiles.toCollection(ArrayList()))
                            startActivity(this)
                        }
                    }
                }
                else {
//                    if(listFiles.isNotEmpty()) {
//                        Intent(this.requireContext(), TraceDrawActivity::class.java).apply {
//                            putExtra("listImage", listFiles)
//                            startActivity(this)
//                        }
//                    }
                }
            }
        }
    }

    override fun initData() {

    }

    override fun initEvent() {
        binding.searchIcon.setOnClickListener {
            Intent(this.requireContext(), HowToUseActivity::class.java).apply {
                startActivity(this)
            }
        }
    }

    override fun observeData() {
        viewModel.currentSelectedCategory.onEach {
            updateCategoryView(it)
        }.launchIn(viewLifecycleOwner.lifecycleScope)

        viewModel.dataList.onEach {
            mTemplateAdapter.updateDataList(it.take(9))
            mLessonsAdapter.updateDataList(viewModel.rawListData.filter { it.category?.contains("Level") == true }
                .take(9))
        }.launchIn(viewLifecycleOwner.lifecycleScope)

    }

    private fun updateCategoryView(selectedCategory: TemplateCategory) {
        for (category in TemplateCategory.entries) {
            val textView = binding.ctnCategories.findViewById<TextView>(category.ordinal)
            textView?.let {
                textView.isSelected = category == selectedCategory
            }
        }
    }
}