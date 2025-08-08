package com.dktech.baseandroidviewdktech.data.model

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable


@Serializable
data class PaintingDrawDTO(
    @SerializedName("id") var id: Int? = null,
    @SerializedName("name") var name: String? = null,
    @SerializedName("category") var category: String? = null,
    @SerializedName("image_url") var imageUrl: ArrayList<String> = arrayListOf(),
    @SerializedName("image_url_new") var imageUrlNew: ArrayList<ImageUrlNew> = arrayListOf(),
    @SerializedName("tab") var tab: String? = null,
    @SerializedName("level") var level: Int? = null,
    @SerializedName("step") var step: Int? = null,
    @SerializedName("mediatype") var mediatype: String? = null,
    @SerializedName("package_name") var packageName: String? = null,
    @SerializedName("thump_url") var thumpUrl: String? = null,
    @SerializedName("thump_filter") var thumpFilter: String? = null,
    @SerializedName("filter_name") var filterName: String? = null,
    @SerializedName("username") var username: String? = null,
    @SerializedName("text_des") var textDes: String? = null,
    @SerializedName("img_option_url") var imgOptionUrl: ArrayList<String> = arrayListOf(),
    @SerializedName("img_result_url") var imgResultUrl: ArrayList<String> = arrayListOf()
)

@Serializable
data class ImageUrlNew(
    @SerializedName("url") var url: String? = null,
    @SerializedName("thumb") var thumb: String? = null,
    @SerializedName("status") var status: Int? = null

)