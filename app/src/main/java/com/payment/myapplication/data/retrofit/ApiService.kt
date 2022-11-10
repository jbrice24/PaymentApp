package com.payment.myapplication.data.retrofit

import com.payment.myapplication.data.model.banks.BankItemDTO
import com.payment.myapplication.data.model.paymentType.ItemDTO
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("/v1/payment_methods")
    suspend fun fetchPaymentType(@Query("public_key") publicKey: String): List<ItemDTO>

    @GET("/v1/payment_methods/card_issuers")
    suspend fun fetchBanks(
        @Query("public_key") publicKey: String,
        @Query("payment_method_id") paymentMethodId: String
    ): List<BankItemDTO>

}