package com.payment.myapplication.data

import com.payment.myapplication.data.model.banks.BankItemDTO
import com.payment.myapplication.data.model.fee.FeeItemDTO
import com.payment.myapplication.data.model.fee.FeeRequestDTO
import com.payment.myapplication.data.model.paymentType.ItemDTO
import com.payment.myapplication.data.sourceFactory.DataSourceFactory
import com.payment.myapplication.domain.repository.Repository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DataRepository @Inject constructor(private val sourceFactory: DataSourceFactory) :
    Repository {

    override suspend fun fetchPaymentType(): Flow<List<ItemDTO>> {
        return sourceFactory.getRemote().fetchPaymentType()
    }

    override suspend fun fetchBanks(paymentMethodId: String): Flow<List<BankItemDTO>> {
        return sourceFactory.getRemote().fetchBanks(paymentMethodId)

    }

    override suspend fun fetchFee(request: FeeRequestDTO): Flow<List<FeeItemDTO>> {
        return sourceFactory.getRemote().fetchFee(request)
    }
}