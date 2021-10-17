package br.com.brunoti.kotlincoinconverter.data.model

import java.util.*

enum class Coin(val locale: Locale) {
	USD(Locale.US),
	CAD(Locale.CANADA),
	BRL(Locale("pt", "BR")),
	ARS(Locale("es", "AR"))
}