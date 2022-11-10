package com.payment.myapplication.data.model.banks

import com.google.gson.annotations.SerializedName

data class BankItemDTO(
    @SerializedName("id") val id: String?,
    @SerializedName("name") val name: String?,
    @SerializedName("processing_mode") val processingMode: String?,
    @SerializedName("secure_thumbnail") val secureThumbnail: String?,
    @SerializedName("status") val status: String?,
    @SerializedName("thumbnail") val thumbnail: String?
)