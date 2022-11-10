package com.payment.myapplication.data.model.fee

import com.google.gson.annotations.SerializedName

data class PayerCost(
    @SerializedName("discount_rate") val discountRate: Double?,
    @SerializedName("installment_amount") val installmentAmount: Double?,
    @SerializedName("installment_rate") val installmentRate: Double?,
    @SerializedName("installment_rate_collector") val installmentRateCollector: List<String>?,
    @SerializedName("installments") val installments: Double?,
    @SerializedName("labels") val labels: List<String>?,
    @SerializedName("max_allowed_amount") val maxAllowedAmount: Double?,
    @SerializedName("min_allowed_amount") val minAllowedAmount: Double?,
    @SerializedName("payment_method_option_id") val paymentMethodOptionId: String?,
    @SerializedName("recommended_message") val recommendedMessage: String?,
    @SerializedName("total_amount") val totalAmount: Double?
)