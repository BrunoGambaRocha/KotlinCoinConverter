package br.com.brunoti.kotlincoinconverter.domain

import br.com.brunoti.kotlincoinconverter.core.UseCase
import br.com.brunoti.kotlincoinconverter.data.model.ExchangeResponseValue
import br.com.brunoti.kotlincoinconverter.data.repository.CoinRepository
import kotlinx.coroutines.flow.Flow

class ListExchangeUseCase(
	private val repository: CoinRepository
) : UseCase.NoParam<List<ExchangeResponseValue>>() {

	override suspend fun execute(): Flow<List<ExchangeResponseValue>> {
		return repository.list()
	}
}