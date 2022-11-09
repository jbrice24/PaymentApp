package com.payment.myapplication.data.retrofit

import com.payment.myapplication.data.model.ItemDTO
import com.payment.myapplication.data.model.PaymentTypeDTO
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("/v1/payment_methods")
    suspend fun fetchPaymentType(@Query("public_key") publicKey: String): List<ItemDTO>

}