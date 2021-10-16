package br.com.brunoti.kotlincoinconverter.domain

import br.com.brunoti.kotlincoinconverter.core.UseCase
import br.com.brunoti.kotlincoinconverter.data.model.ExchangeResponseValue
import br.com.brunoti.kotlincoinconverter.data.repository.CoinRepository
import kotlinx.coroutines.flow.Flow

class GetExchangeValueUseCase (
	private val repository: CoinRepository
) : UseCase<String, ExchangeResponseValue>() {

	override suspend fun execute(param: String): Flow<ExchangeResponseValue> {
		return repository.getExchangeValue(param)
	}

}