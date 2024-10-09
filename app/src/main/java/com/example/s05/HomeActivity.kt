package com.example.s05

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity


class HomeActivity : AppCompatActivity() {

    private val exchangeRates = mapOf(
        "PEN" to 1.0,   // Sol peruano (moneda base)
        "USD" to 0.27,
        "EUR" to 0.24,
        "MXN" to 5.29
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_activity)

        val editTextAmount: EditText = findViewById(R.id.editTextAmount)
        val spinnerFrom: Spinner = findViewById(R.id.spinnerFrom)
        val spinnerTo: Spinner = findViewById(R.id.spinnerTo)
        val buttonConvert: Button = findViewById(R.id.buttonConvert)
        val textViewResult: TextView = findViewById(R.id.textViewResult)

        val currencies = arrayOf("PEN", "USD", "EUR", "MXN")

        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, currencies)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerFrom.adapter = adapter
        spinnerTo.adapter = adapter

        buttonConvert.setOnClickListener {
            val amountText = editTextAmount.text.toString()

            if (amountText.isEmpty()) {
                Toast.makeText(this, "Por favor, ingrese una cantidad", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val amount = amountText.toDouble()

            val fromCurrency = spinnerFrom.selectedItem.toString()
            val toCurrency = spinnerTo.selectedItem.toString()

            val result = convertCurrency(amount, fromCurrency, toCurrency)

            textViewResult.text = String.format("%.2f %s", result, toCurrency)
        }
    }

    private fun convertCurrency(amount: Double, fromCurrency: String, toCurrency: String): Double {
        val fromRate = exchangeRates[fromCurrency] ?: 1.0
        val toRate = exchangeRates[toCurrency] ?: 1.0
        return (amount / fromRate) * toRate
    }
}