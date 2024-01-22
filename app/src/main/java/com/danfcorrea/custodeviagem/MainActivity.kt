package com.danfcorrea.custodeviagem

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.google.android.material.textfield.TextInputLayout
import com.google.android.material.textview.MaterialTextView

class MainActivity : AppCompatActivity() {

    private var distanceValue: Double = 0.0
    private var priceValue: Double = 0.0
    private var consumeValue: Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val calcButton = findViewById<Button>(R.id.calc)
        calcButton.setOnClickListener { click() }
    }

    private fun click() {
        loadValues()
        returnValues(
            findViewById(R.id.result),
            String.format("%.2f", calculate(distanceValue, priceValue, consumeValue))
        )
    }

    private fun calculate(distance: Double, price: Double, consume: Double): Double {
        return distance / consume * price
    }

    private fun loadValues() {
        distanceValue = findViewById<TextInputLayout>(R.id.distance)
            .editText?.text.toString().replace(',', '.').toDouble()
        priceValue = findViewById<TextInputLayout>(R.id.price)
            .editText?.text.toString().replace(',', '.').toDouble()
        consumeValue = findViewById<TextInputLayout>(R.id.consumption)
            .editText?.text.toString().replace(',', '.').toDouble()
    }

    private fun returnValues(view: MaterialTextView, valor: String) {
        view.text = buildString {
            append(valor)
            append(" R$")
        }
    }

}