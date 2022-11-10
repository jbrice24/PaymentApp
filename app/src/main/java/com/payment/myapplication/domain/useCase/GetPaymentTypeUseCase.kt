package com.payment.myapplication.domain.useCase

import com.payment.myapplication.domain.repository.Repository
import com.payment.myapplication.presentation.model.PaymentType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetPaymentTypeUseCase @Inject constructor(private val repository: Repository){

    suspend fun execute() : Flow<List<PaymentType>>{
        return repository.fetchPaymentType().map {
            it.map { item ->
                PaymentType(
                    paymentId= item.id,
                    paymentTypeName = item.name,
                    paymentTypeImage = item.secureThumbnail
                )
            }
        }
    }

}