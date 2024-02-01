package com.danfcorrea.custodeviagem

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.google.android.material.textfield.TextInputLayout
import com.google.android.material.textview.MaterialTextView

class MainActivity : AppCompatActivity() {

    private var distanceValue: Double? = null
    private var priceValue: Double? = null
    private var consumeValue: Double? = null
    private lateinit var distance: TextInputLayout
    private lateinit var price: TextInputLayout
    private lateinit var consume: TextInputLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        distance = findViewById(R.id.distance)
        price = findViewById(R.id.price)
        consume = findViewById(R.id.consumption)

        val calcButton = findViewById<Button>(R.id.calc)
        calcButton.setOnClickListener { click() }

    }

    private fun click() {
        if(loadValues()){
            returnValues(
                findViewById(R.id.result),findViewById(R.id.resultText),
                String.format("%.2f", calculate(distanceValue!!, priceValue!!, consumeValue!!))
            )
        }
    }

    private fun calculate(distance: Double, price: Double, consume: Double): Double {
        return distance / consume * price
    }

    private fun loadValues(): Boolean {
        distanceValue = distance.editText?.text.toString().replace(',', '.').toDoubleOrNull()
        priceValue = price.editText?.text.toString().replace(',', '.').toDoubleOrNull()
        consumeValue = consume.editText?.text.toString().replace(',', '.').toDoubleOrNull()
        checkFields(distance, price, consume)
        return !(distanceValue == null || priceValue == null || consumeValue == null)
    }

    private fun returnValues(view: MaterialTextView, text: MaterialTextView, valor: String) {
        view.visibility = View.VISIBLE
        text.visibility = View.VISIBLE
        view.text = buildString {
            append("R$ ")
            append(valor)
        }

    }

    private fun checkFields(vararg view: TextInputLayout){
        for(v in view){
            if(v.editText?.text.toString().toDoubleOrNull() == null){
                v.error = getString(R.string.error)
                v.editText!!.text.clear()
            }else{
                v.error = null
            }
        }
    }

}