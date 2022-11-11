package com.payment.myapplication.data.remote

import com.payment.myapplication.BuildConfig
import com.payment.myapplication.data.model.banks.BankItemDTO
import com.payment.myapplication.data.model.fee.FeeItemDTO
import com.payment.myapplication.data.model.fee.FeeRequestDTO
import com.payment.myapplication.data.model.paymentType.ItemDTO
import com.payment.myapplication.data.retrofit.ApiService
import com.payment.myapplication.data.source.Remote
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RemoteImpl @Inject constructor(private val apiService: ApiService)
    : Remote{

    override suspend fun fetchPaymentType(): Flow<List<ItemDTO>> {
        return flow {
            emit(apiService.fetchPaymentType(BuildConfig.API_KEY_MELI))
        }
    }

    override suspend fun fetchBanks(paymentMethodId: String): Flow<List<BankItemDTO>> {
        return flow {
            emit(apiService.fetchBanks(BuildConfig.API_KEY_MELI, paymentMethodId))
        }
    }

    override suspend fun fetchFee(request: FeeRequestDTO): Flow<List<FeeItemDTO>> {
        return flow {
            emit(
                apiService.fetchFees(
                    publicKey = BuildConfig.API_KEY_MELI,
                    amount = request.amount.toString(),
                    paymentMethodId = request.paymentId,
                    issuerId = request.issuerId
                )
            )
        }
    }
}