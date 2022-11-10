package com.payment.myapplication.data.model.fee

import com.google.gson.annotations.SerializedName

data class Issuer(
    @SerializedName("id") val id: String?,
    @SerializedName("name") val name: String?,
    @SerializedName("secure_thumbnail") val secureThumbnail: String?,
    @SerializedName("thumbnail") val thumbnail: String?
)