package com.dktech.baseandroidviewdktech.ui.home.adapter

import android.annotation.SuppressLint
import android.view.Gravity
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.LinearLayout.LayoutParams
import androidx.core.view.marginTop
import androidx.recyclerview.widget.RecyclerView
import com.dktech.baseandroidviewdktech.R
import com.dktech.baseandroidviewdktech.databinding.FragmentLessonBinding
import com.dktech.baseandroidviewdktech.databinding.FragmentLessonItemBinding
import com.dktech.baseandroidviewdktech.databinding.ItemLessonBinding
import com.dktech.baseandroidviewdktech.ui.home.adapter.LessonItemAdapter.LessonItemViewHolder
import com.dktech.baseandroidviewdktech.ui.home.model.LessonUIWrapper
import com.dktech.baseandroidviewdktech.utils.helper.loadRemoteImage
import com.dktech.baseandroidviewdktech.utils.helper.pxFromDp


class LessonItemAdapter : RecyclerView.Adapter<LessonItemViewHolder>() {
    private var dataList: List<LessonUIWrapper> = emptyList()

    inner class LessonItemViewHolder(
        private val binding: FragmentLessonItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(item: LessonUIWrapper) {
            binding.image.loadRemoteImage(
                binding.root.context,
                item.thumbImage
            )
            binding.tvStep.text = item.imageStepList.size.toString()
            binding.cntRating.removeAllViews()
            if(item.level != 0){
                for(i in 1..3){
                    if(i <= item.level){
                        binding.cntRating.addView(
                            ImageView(
                                binding.root.context
                            ).apply {
                                layoutParams = LinearLayout.LayoutParams(
                                    LayoutParams.WRAP_CONTENT,
                                    LayoutParams.WRAP_CONTENT,
                                ).apply {
                                    gravity = Gravity.CENTER

                                }
                                maxWidth = binding.root.context.pxFromDp(12f).toInt()
                                maxHeight = binding.root.context.pxFromDp(12f).toInt()
                                setPadding(
                                    binding.root.context.pxFromDp(2f).toInt(),
                                    0,
                                    binding.root.context.pxFromDp(2f).toInt(),
                                    0
                                )
                                setImageResource(R.drawable.star_filled)
                            }
                        )
                    }else{
                        binding.cntRating.addView(
                            ImageView(
                                binding.root.context
                            ).apply {
                                layoutParams = LinearLayout.LayoutParams(
                                    LayoutParams.WRAP_CONTENT,
                                    LayoutParams.WRAP_CONTENT,
                                ).apply {
                                    gravity = Gravity.CENTER
                                }
                                maxWidth = binding.root.context.pxFromDp(12f).toInt()
                                maxHeight = binding.root.context.pxFromDp(12f).toInt()
                                setPadding(
                                    binding.root.context.pxFromDp(2f).toInt(),
                                    0,
                                    binding.root.context.pxFromDp(2f).toInt(),
                                    0
                                )
                                setImageResource(R.drawable.star)
                            }
                        )
                    }
                }
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateDataList(list: List<LessonUIWrapper>) {
        this.dataList = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LessonItemViewHolder {
        return LessonItemViewHolder(
            FragmentLessonItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: LessonItemViewHolder, position: Int) {
        holder.onBind(dataList[position])
    }

}