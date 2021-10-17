package br.com.brunoti.kotlincoinconverter.data.repository

import br.com.brunoti.kotlincoinconverter.core.exceptions.RemoteException
import br.com.brunoti.kotlincoinconverter.data.model.ErrorResponse
import br.com.brunoti.kotlincoinconverter.data.services.AwesomeService
import com.google.gson.Gson
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException

class CoinRepositoryImpl (
	private val service: AwesomeService
) : CoinRepository {

	override suspend fun getExchangeValue(coins: String) = flow {
		try {
			val exchangeValue = service.exchangeValue(coins)
			val exchange = exchangeValue.values.first()
			emit(exchange)
		} catch (e: HttpException) {
			val json = e.response()?.errorBody()?.string()
			val errorResponse = Gson().fromJson(json, ErrorResponse::class.java)
			throw RemoteException(errorResponse.message)
		}
	}
}