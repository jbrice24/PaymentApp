package com.payment.myapplication.domain.useCase

import com.payment.myapplication.data.model.fee.FeeRequestDTO
import com.payment.myapplication.domain.repository.Repository
import com.payment.myapplication.presentation.model.Fee
import com.payment.myapplication.presentation.model.FeeRequest
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetFeeUseCase @Inject constructor(private val repository: Repository) {

    suspend fun execute(request: FeeRequest): Flow<List<Fee>> {
        val feeDataRequest = FeeRequestDTO(
            amount = request.amount,
            paymentId = request.paymentId,
            issuerId = request.issuerId
        )

        return repository.fetchFee(feeDataRequest).map {
            it.firstOrNull().let { feeItem ->
                feeItem?.payerCosts?.let { list ->
                    list.map { payerCost ->
                        Fee(
                            message = payerCost.recommendedMessage.orEmpty()
                        )
                    }
                }
            }.orEmpty()
        }
    }

}