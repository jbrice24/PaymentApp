package com.payment.myapplication.domain.repository

import com.payment.myapplication.data.model.ItemDTO
import kotlinx.coroutines.flow.Flow

interface Repository {

    suspend fun fetchPaymentType() : Flow<List<ItemDTO>>

}