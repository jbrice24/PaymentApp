package com.payment.myapplication.domain.repository

import com.payment.myapplication.data.model.banks.BankItemDTO
import com.payment.myapplication.data.model.paymentType.ItemDTO
import kotlinx.coroutines.flow.Flow

interface Repository {

    suspend fun fetchPaymentType() : Flow<List<ItemDTO>>
    suspend fun fetchBanks(paymentMethodId: String) : Flow<List<BankItemDTO>>

}