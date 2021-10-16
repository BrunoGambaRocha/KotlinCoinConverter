package br.com.brunoti.kotlincoinconverter.ui

import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doAfterTextChanged
import br.com.brunoti.kotlincoinconverter.core.extensions.createDialog
import br.com.brunoti.kotlincoinconverter.core.extensions.createProgressDialog
import br.com.brunoti.kotlincoinconverter.core.extensions.text
import br.com.brunoti.kotlincoinconverter.data.model.Coin
import br.com.brunoti.kotlincoinconverter.databinding.ActivityMainBinding
import br.com.brunoti.kotlincoinconverter.presentation.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

	private val viewModel by viewModel<MainViewModel>()
	private val dialog by lazy { createProgressDialog() }
	private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		setContentView(binding.root)

		bindAdapters()
		bindListeners()

		viewModel.getExchangeValue("USD-BRL")
		viewModel.state.observe(this) {
			when (it) {
				MainViewModel.State.Loading -> dialog.show()
				is MainViewModel.State.Error -> {
					dialog.dismiss()
					createDialog {
						setMessage(it.error.message)
					}.show()
				}
				is MainViewModel.State.Success -> {
					dialog.dismiss()
					Log.e("TAG", "onCreate: ${it.value}")
				}
			}
		}
	}

	private fun bindAdapters() {
		val list = Coin.values()
		val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, list)

		binding.tvFrom.setAdapter(adapter)
		binding.tvTo.setAdapter(adapter)

		binding.tvFrom.setText(Coin.BRL.name, false)
		binding.tvTo.setText(Coin.USD.name, false)
	}

	private fun bindListeners() {
		binding.tilValue.editText?.doAfterTextChanged {
			binding.btnConverter.isEnabled = it != null && it.toString().isNotEmpty()
		}

		binding.btnConverter.setOnClickListener {
			Log.e("TAG", "bindListeners: " + binding.tilValue.text)
		}
	}
}