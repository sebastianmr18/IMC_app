package com.example.imc_app

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.EditText
import android.widget.Button
import android.widget.Toast
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import com.example.imc_app.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.apply {
            binding.editTextWeight.setOnFocusChangeListener { _, hasFocus ->
                if (hasFocus && binding.editTextWeight.text.toString() == "Ejemplo: 70") {
                    binding.editTextWeight.text = null
                }
            }

            binding.editTextHeight.setOnFocusChangeListener { _, hasFocus ->
                if (hasFocus && binding.editTextHeight.text.toString() == "Ejemplo: 170") {
                    binding.editTextHeight.text = null
                }
            }

            binding.buttonCalculate.setOnClickListener {
                val height = binding.editTextHeight.text.toString().toFloatOrNull()
                val weight = binding.editTextWeight.text.toString().toFloatOrNull()

                if (weight != null && height != null && height != 0f){
                    val heightInMeters = height / 100
                    val imc = weight / (heightInMeters * heightInMeters)
                    binding.textViewIMCValue.text = "Tu IMC es: %.2f".format(imc) // Actualiza el valor del IMC
                    if (imc < 18.5) {
                        binding.textViewIMCType.text = "Tu peso es bajo"
                    } else if (imc < 24.9) {
                        binding.textViewIMCType.text = "Tu peso es normal"
                    } else {
                        binding.textViewIMCType.text = "Tienes sobrepeso"
                    }
                } else {
                    Toast.makeText(this@MainActivity, "Ingrese valores validos", Toast.LENGTH_LONG).show()
                }
            }
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}