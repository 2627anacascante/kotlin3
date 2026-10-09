package com.example.activitat3


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import com.example.activitat3.R
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.activitat3.ResultActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.slider.Slider
import java.text.DecimalFormat
import kotlin.jvm.java

class CalculatorActivity : AppCompatActivity() {

    private var seleccioHome: Boolean = true
    private var seleccioDona: Boolean = false
    private var pesActual: Int = 70
    private var edatActual: Int = 30
    private var alturaActual: Int = 120
    private lateinit var cardHome: CardView
    private lateinit var cardDona: CardView
    private lateinit var textAltura: TextView
    private lateinit var barraAltura: Slider
    private lateinit var botoAprimar: FloatingActionButton
    private lateinit var botoEngreixar: FloatingActionButton
    private lateinit var textPes: TextView
    private lateinit var botoRejuvenir: FloatingActionButton
    private lateinit var botoEnvellir: FloatingActionButton
    private lateinit var textEdat: TextView
    private lateinit var botoCalcular: Button

    companion object {
        const val IMC = "IMC_RESULT"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)// crea la vista
        enableEdgeToEdge()// permite que la vista se ajuste a la pantalla
        setContentView(R.layout.calculator)// fija la vista en el activity
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        //ViewCompat.setOnApplyWindowInsetsListener(...): Asigna un escuchador (listener) a una vista para reaccionar cuando el sistema
        // operativo calcula los bordes o áreas reservadas en pantalla.
        //findViewById(R.id.main): Es la vista objetivo a la que se aplicará el listener (tu ConstraintLayout contenedor principal).
        // v, insets ->: Es una función lambda que se ejecuta automáticamente cuando la pantalla se dibuja y recibe dos parámetros:
        //v: Representa a la propia vista (R.id.main).
        //insets: Es el objeto que contiene las dimensiones de los recortes y barras del sistema (barra de estado, barra de navegación, muesca de la cámara, etc.).
        initComponents()// inicializa los componentes
        initListeners()// inicializa los listeners
        initUI()// inicializa la interfaz de usuario
    }

    private fun initComponents() {
        cardHome = findViewById(R.id.cardHome)
        cardDona = findViewById(R.id.cardDona)
        textAltura = findViewById(R.id.textAltura)
        barraAltura = findViewById(R.id.barraAltura)
        botoAprimar = findViewById(R.id.botoAprimar)
        botoEngreixar = findViewById(R.id.botoEngreixar)
        textPes = findViewById(R.id.textPes)
        botoRejuvenir = findViewById(R.id.botoRejuvenir)
        botoEnvellir = findViewById(R.id.botoEnvellir)
        textEdat = findViewById(R.id.textEdat)
        botoCalcular = findViewById(R.id.botoCalcular)
    }

    private fun initListeners() {
        cardHome.setOnClickListener {
            canviarGenere()
            setGenderColor()
        }
        cardDona.setOnClickListener {
            canviarGenere()
            setGenderColor()
        }
        barraAltura.addOnChangeListener { _, value, _ ->
            val df = DecimalFormat("#.##")
            alturaActual = df.format(value).toInt()
            textAltura.text = "$alturaActual cm"
        }
        botoEngreixar.setOnClickListener {
            pesActual += 1
            setWeight()
        }
        botoAprimar.setOnClickListener {
            pesActual -= 1
            setWeight()
        }
        botoEnvellir.setOnClickListener {
            edatActual += 1
            setAge()
        }
        botoRejuvenir.setOnClickListener {
            edatActual -= 1
            setAge()
        }
        botoCalcular.setOnClickListener {
            val result = calcularIMC()
            navigateToResult(result)
        }
    }

    private fun navigateToResult(result: Double) {
        val intent = Intent(this, ResultActivity::class.java)
        intent.putExtra(IMC, result)
        startActivity(intent)
    }

    private fun calcularIMC():Double {
        val df = DecimalFormat("#.##")
        val imc = pesActual / (alturaActual.toDouble() / 100 * alturaActual.toDouble() / 100)
        return df.format(imc).toDouble()
    }

    private fun setAge() {
        textEdat.text = edatActual.toString()
    }

    private fun setWeight() {
        textPes.text = pesActual.toString()
    }

    private fun canviarGenere() {
        seleccioHome = !seleccioHome
        seleccioDona = !seleccioDona
    }

    private fun setGenderColor() {
        cardHome.setCardBackgroundColor(getBackgroundColor(seleccioHome))
        cardDona.setCardBackgroundColor(getBackgroundColor(seleccioDona))
    }

    private fun getBackgroundColor(isSelectedComponent: Boolean): Int {

        val colorReference = if (isSelectedComponent) {
            R.color.fons_seleccionat
        } else {
            R.color.fons
        }

        return ContextCompat.getColor(this, colorReference)
    }


    private fun initUI() {
        setGenderColor()
        setWeight()
        setAge()
    }
}
