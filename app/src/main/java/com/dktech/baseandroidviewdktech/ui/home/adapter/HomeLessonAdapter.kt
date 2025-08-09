package com.dktech.baseandroidviewdktech.ui.home.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dktech.baseandroidviewdktech.data.model.PaintingDrawDTO
import com.dktech.baseandroidviewdktech.databinding.ItemLessonBinding
import com.dktech.baseandroidviewdktech.databinding.ItemTemplateBinding
import com.dktech.baseandroidviewdktech.ui.home.adapter.HomeLessonAdapter.HomeLessonViewHolder
import com.dktech.baseandroidviewdktech.utils.helper.loadRemoteImage

class HomeLessonAdapter constructor(
    private val onItemClick: (PaintingDrawDTO) -> Unit
): RecyclerView.Adapter<HomeLessonViewHolder>() {


    private var dataList: List<PaintingDrawDTO> = emptyList()

    inner class HomeLessonViewHolder(
        private val binding: ItemLessonBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(url: PaintingDrawDTO) {
            binding.image.loadRemoteImage(
                binding.root.context,
                url.imageUrl.last()
            )
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateDataList(list: List<PaintingDrawDTO>) {
        this.dataList = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeLessonViewHolder {
        return HomeLessonViewHolder(
            ItemLessonBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: HomeLessonViewHolder, position: Int) {
        holder.onBind(dataList[position])
    }
}