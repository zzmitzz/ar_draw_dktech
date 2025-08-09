package com.dktech.baseandroidviewdktech.ui.home.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dktech.baseandroidviewdktech.databinding.ItemTemplateBinding
import com.dktech.baseandroidviewdktech.utils.helper.loadRemoteImage

class HomeTemplateAdapter : RecyclerView.Adapter<HomeTemplateAdapter.HomeTemplateViewHolder>() {


    private var dataList: List<String> = emptyList()

    inner class HomeTemplateViewHolder(
        private val binding: ItemTemplateBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(url: String) {
            binding.image.loadRemoteImage(
                binding.root.context,
                url
            )
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateDataList(list: List<String>) {
        this.dataList = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeTemplateViewHolder {
        return HomeTemplateViewHolder(
            ItemTemplateBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: HomeTemplateViewHolder, position: Int) {
        holder.onBind(dataList[position])
    }
}