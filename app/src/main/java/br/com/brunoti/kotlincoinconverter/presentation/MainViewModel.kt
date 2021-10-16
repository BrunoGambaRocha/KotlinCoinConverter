package br.com.brunoti.kotlincoinconverter.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.brunoti.kotlincoinconverter.data.model.ExchangeResponseValue
import br.com.brunoti.kotlincoinconverter.domain.GetExchangeValueUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class MainViewModel (
	private val getExchangeValueUseCase: GetExchangeValueUseCase
) : ViewModel() {

	private val _state = MutableLiveData<State>()
	val state: LiveData<State> = _state

	fun getExchangeValue(coins: String) {
		viewModelScope.launch {
			getExchangeValueUseCase(coins)
				.flowOn(Dispatchers.Main)
				.onStart {
					_state.value = State.Loading
				}
				.catch {
					_state.value = State.Error(it)
				}
				.collect() {
					_state.value = State.Success(it)
				}
		}
	}

	sealed class State {
		object Loading: State()

		data class Success(val value: ExchangeResponseValue): State()
		data class Error(val error: Throwable): State()
	}
}