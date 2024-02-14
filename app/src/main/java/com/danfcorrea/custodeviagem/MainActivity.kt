package com.danfcorrea.custodeviagem

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
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

        distance = findViewById(R.id.input_distance)
        price = findViewById(R.id.input_price)
        consume = findViewById(R.id.Input_autonomy)

        val calcButton = findViewById<Button>(R.id.button_calculate)
        calcButton.setOnClickListener { click() }

    }

    private fun click() {
        if(loadValues()){
            returnValues(
                findViewById(R.id.text_result),findViewById(R.id.linear_result),
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

    private fun returnValues(text: MaterialTextView, view: LinearLayout, valor: String) {
        view.visibility = View.VISIBLE
        text.text = buildString {
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