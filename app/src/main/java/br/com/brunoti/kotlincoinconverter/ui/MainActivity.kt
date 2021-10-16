package br.com.brunoti.kotlincoinconverter.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.brunoti.kotlincoinconverter.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}