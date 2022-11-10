package com.payment.myapplication.data.model.fee

import com.google.gson.annotations.SerializedName

data class FeeItemDTO(
    @SerializedName("issuer") val issuer: Issuer?,
    @SerializedName("payer_costs") val payerCosts: List<PayerCost>?,
    @SerializedName("payment_method_id") val paymentMethodId: String?,
    @SerializedName("payment_type_id") val paymentTypeId: String?,
    @SerializedName("processing_mode") val processingMode: String?
)