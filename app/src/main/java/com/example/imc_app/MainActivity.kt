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

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // Referencias a los EditText en el layout
        val editTextWeight = findViewById<EditText>(R.id.editTextWeight)
        val editTextHeight = findViewById<EditText>(R.id.editTextHeight)
        val buttonCalculate = findViewById<Button>(R.id.buttonCalculate)
        val textViewIMCValue = findViewById<TextView>(R.id.textViewIMCValue)
        val textViewIMCType = findViewById<TextView>(R.id.textViewIMCType)

        // Listener para limpiar el texto por defecto al enfocarse
        editTextWeight.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus && editTextWeight.text.toString() == "Ejemplo: 70") {
                editTextWeight.text = null
            }
        }

        editTextHeight.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus && editTextHeight.text.toString() == "Ejemplo: 170") {
                editTextHeight.text = null
            }
        }

        // Listener para el botón Calcular
        buttonCalculate.setOnClickListener {
            val height = editTextHeight.text.toString().toFloatOrNull()
            val weight = editTextWeight.text.toString().toFloatOrNull()

            if (weight != null && height != null && height != 0f){
                val heightInMeters = height / 100
                val imc = weight / (heightInMeters * heightInMeters)
                textViewIMCValue.text = "Tu IMC es: %.2f".format(imc) // Actualiza el valor del IMC
                if (imc < 18.5) {
                    textViewIMCType.text = "Tu peso es bajo"
                } else if (imc < 24.9) {
                    textViewIMCType.text = "Tu peso es normal"
                } else {
                    textViewIMCType.text = "Tienes sobrepeso"
                }
            } else {
                Toast.makeText(this, "Ingrese valores validos", Toast.LENGTH_LONG).show()
            }
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}