package com.payment.myapplication.data.source

import com.payment.myapplication.data.model.banks.BankItemDTO
import com.payment.myapplication.data.model.fee.FeeItemDTO
import com.payment.myapplication.data.model.fee.FeeRequestDTO
import com.payment.myapplication.data.model.paymentType.ItemDTO
import kotlinx.coroutines.flow.Flow

interface Remote {

    suspend fun fetchPaymentType() : Flow<List<ItemDTO>>
    suspend fun fetchBanks(paymentMethodId: String) : Flow<List<BankItemDTO>>
    suspend fun fetchFee(request: FeeRequestDTO) : Flow<List<FeeItemDTO>>

}