package com.payment.myapplication.data

import com.payment.myapplication.BuildConfig
import com.payment.myapplication.data.model.ItemDTO
import com.payment.myapplication.data.retrofit.ApiService
import com.payment.myapplication.domain.repository.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DataRepository @Inject constructor(private val apiService: ApiService) : Repository {

    override suspend fun fetchPaymentType(): Flow<List<ItemDTO>> {
        return flow {
            emit(apiService.fetchPaymentType(BuildConfig.API_KEY_MELI))
        }
    }
}