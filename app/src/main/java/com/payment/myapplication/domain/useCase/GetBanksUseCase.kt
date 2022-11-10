package com.payment.myapplication.domain.useCase

import com.payment.myapplication.domain.model.Bank
import com.payment.myapplication.domain.repository.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetBanksUseCase @Inject constructor(private val repository: Repository) {

    suspend fun execute(paymentMethodId: String) : Flow<List<Bank>>{
        return repository.fetchBanks(paymentMethodId).map {
            it.map { bank ->
                Bank(
                    name = bank.name,
                    image = bank.secureThumbnail
                )
            }
        }
    }

}