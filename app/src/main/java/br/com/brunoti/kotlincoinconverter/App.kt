package br.com.brunoti.kotlincoinconverter

import android.app.Application
import br.com.brunoti.kotlincoinconverter.data.di.DataModules
import br.com.brunoti.kotlincoinconverter.domain.di.DomainModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

	override fun onCreate() {
		super.onCreate()

		startKoin {
			androidContext(this@App)
		}

		DataModules.load()
		DomainModule.load()
	}
}