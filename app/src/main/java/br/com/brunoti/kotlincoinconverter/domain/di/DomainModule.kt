package br.com.brunoti.kotlincoinconverter.domain.di

import br.com.brunoti.kotlincoinconverter.domain.GetExchangeValueUseCase
import br.com.brunoti.kotlincoinconverter.domain.ListExchangeUseCase
import br.com.brunoti.kotlincoinconverter.domain.SaveExchangeUseCase
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module

object DomainModule {

	fun load() {
		loadKoinModules(useCaseModules())
	}

	private fun useCaseModules(): Module {
		return module {
			factory { ListExchangeUseCase(get()) }
			factory { SaveExchangeUseCase(get()) }
			factory { GetExchangeValueUseCase(get()) }
		}
	}
}