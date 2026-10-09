package com.example.activitat3


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.example.activitat3.CalculatorActivity.Companion.IMC


class ResultActivity : AppCompatActivity() {

    private lateinit var textResultat:TextView
    private lateinit var textIMC:TextView
    private lateinit var textDescripcio:TextView
    private lateinit var botoRecalcular:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.result)
        val result:Double = intent.extras?.getDouble(IMC) ?: -1.0
        initComponents()
        initUI(result)
        initListeners()
    }

    private fun initListeners() {
        botoRecalcular.setOnClickListener { finish() }
    }

    private fun initUI(result: Double) {
        textIMC.text = result.toString()
        when(result){
            in 0.00..18.50 -> { //Bajo peso
                textResultat.text = getString(R.string.baix_pes)
                textResultat.setTextColor(ContextCompat.getColor(this, R.color.peso_bajo))
                textDescripcio.text = getString(R.string.descripcio_baix_pes)
            }
            in 18.51..24.99 -> { //Peso normal
                textResultat.text = getString(R.string.pes_normal)
                textResultat.setTextColor(ContextCompat.getColor(this, R.color.peso_normal))
                textDescripcio.text = getString(R.string.descripcio_pes_normal)
            }
            in 25.00..29.99 -> { //Sobrepeso
                textResultat.text = getString(R.string.sobrepes)
                textResultat.setTextColor(ContextCompat.getColor(this, R.color.peso_sobrepeso))
                textDescripcio.text = getString(R.string.descripcio_sobrepes)
            }
            in 30.00..99.00 -> { //Obesidad
                textResultat.text = getString(R.string.obesitat)
                textResultat.setTextColor(ContextCompat.getColor(this, R.color.obesidad))
                textDescripcio.text = getString(R.string.descripcio_obesitat)
            }
            else -> {//error
                textIMC.text = getString(R.string.error)
                textResultat.text = getString(R.string.error)
                textResultat.setTextColor(ContextCompat.getColor(this, R.color.obesidad))
                textDescripcio.text = getString(R.string.error)
            }
        }
    }

    private fun initComponents() {
        textIMC = findViewById(R.id.textIMC)
        textResultat = findViewById(R.id.textResultat)
        textDescripcio = findViewById(R.id.textDescripcio)
        botoRecalcular = findViewById(R.id.botoRecalcular)
    }
}
