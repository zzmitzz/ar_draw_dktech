package com.dktech.baseandroidviewdktech.ui.language_screen

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.RecyclerView
import com.dktech.baseandroidviewdktech.R
import com.dktech.baseandroidviewdktech.base.ui_models.LanguageModel
import com.dktech.baseandroidviewdktech.databinding.ItemLanguageBinding

class LanguageAdapter(
    private val context: Context,
    private val languageList: List<LanguageModel>,
    private val onFirstSelect: () -> Unit
) : RecyclerView.Adapter<LanguageAdapter.ViewHolder>() {

    private var selectedLanguage: LanguageModel? = null
        set(value) {
            if (field == null) {
                onFirstSelect()
            }
            field = value
        }

    inner class ViewHolder(val binding: ItemLanguageBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("NotifyDataSetChanged")
        fun bind(language: LanguageModel) {

            val isSelected = selectedLanguage == language
            if (isSelected) {
                binding.apply {
                    ivRadio.setImageResource(R.drawable.ic_rbtn_2_checked)
                    frFlagCard.backgroundTintList =
                        AppCompatResources.getColorStateList(context, R.color.primaryContainer)
                    viewSpace.backgroundTintList =
                        AppCompatResources.getColorStateList(context, R.color.primaryContainer)
                    layoutLanguage.backgroundTintList =
                        AppCompatResources.getColorStateList(context, R.color.primaryContainer)
                }
            } else {
                binding.apply {
                    ivRadio.setImageResource(R.drawable.ic_rbtn_2)
                    frFlagCard.backgroundTintList =
                        AppCompatResources.getColorStateList(context, R.color.secondary)
                    viewSpace.backgroundTintList =
                        AppCompatResources.getColorStateList(context, R.color.secondary)
                    layoutLanguage.backgroundTintList =
                        AppCompatResources.getColorStateList(context, R.color.secondary)
                }
            }
            binding.ivLanguage.setImageResource(language.flagImage)
            binding.languageName.text = context.getString(language.name)
            binding.languageName.setHorizontallyScrolling(true)
            binding.root.setOnClickListener {
                selectedLanguage = language
                notifyDataSetChanged()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(
            ItemLanguageBinding.inflate(inflater, parent, false)
        )
    }

    @SuppressLint("ResourceAsColor")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val language = languageList[position]
        holder.bind(language)
    }

    fun getSelectedPositionLanguage(): LanguageModel? {
        return selectedLanguage
    }

//    fun updateSelectedLanguage(language: LanguageModel) {
//        if(selectedLanguage == language) return
//        selectedLanguage?.let {
//            notifyItemChanged(languageList.indexOf(it))
//        }
//        selectedLanguage = language
//        notifyItemChanged(languageList.indexOf(language))
//    }

    override fun getItemCount(): Int {
        return languageList.size
    }

}