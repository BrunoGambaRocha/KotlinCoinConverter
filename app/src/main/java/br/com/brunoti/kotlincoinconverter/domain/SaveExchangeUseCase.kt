package br.com.brunoti.kotlincoinconverter.domain

import br.com.brunoti.kotlincoinconverter.core.UseCase
import br.com.brunoti.kotlincoinconverter.data.model.ExchangeResponseValue
import br.com.brunoti.kotlincoinconverter.data.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class SaveExchangeUseCase(
	private val repository: CoinRepository
) : UseCase.NoSource<ExchangeResponseValue>() {

	override suspend fun execute(param: ExchangeResponseValue): Flow<Unit> {
		return flow {
			repository.save(param)
			emit(Unit)
		}
	}
}