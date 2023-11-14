package com.example.vallesvmcc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.textfield.TextInputLayout

class MainActivity : AppCompatActivity() {

    lateinit var stepsV : EditText
    lateinit var pathV : EditText
    lateinit var buttonCalcular: Button
    lateinit var buttonU: Button
    lateinit var buttonD: Button
    lateinit var buttonClean: Button
    lateinit var textViewNV : TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        stepsV = findViewById(R.id.editPasos)
        pathV = findViewById(R.id.editSecuencia)
        textViewNV = findViewById(R.id.textViewNV)

        buttonCalcular = findViewById(R.id.buttonCalcular)

        buttonU = findViewById(R.id.buttonU)
        buttonD = findViewById(R.id.buttonD)
        buttonClean = findViewById(R.id.buttonClean)

        buttonD.setOnClickListener {
            val d= pathV.text.toString()+"D"
            pathV.setText(d)
        }

        buttonU.setOnClickListener {
            val u= pathV.text.toString()+"U"
            pathV.setText(u)
        }

        buttonClean.setOnClickListener {
            pathV.setText("")
            stepsV.setText("")
        }



        buttonCalcular.setOnClickListener {
            if(stepsV.text.toString()!=""&&pathV.text.toString()!=""){
                if(Integer.parseInt(stepsV.text.toString()) == pathV.text.toString().count()){
                    textViewNV.text=calcular(Integer.parseInt(stepsV.text.toString()),pathV.text.toString()).toString()
                }else{
                    Toast.makeText(this, "El número de pasos no coincide con la secuencia", Toast.LENGTH_LONG).show()
                }
            }else{
                Toast.makeText(this, "Ingresa datos", Toast.LENGTH_LONG).show()
            }

        }


    }

    fun calcular(nPasos : Int, secuencia: String) : Int{
        var nValles = 0
        var recorrido = 0  // el recorrido inicia a nivel del mar o punto 0

        val arraySecuencia = secuencia.toCharArray()

        for(i in 0 until nPasos){
            when(arraySecuencia[i]){
                'D'->{ recorrido-- } //cada que bajas se resta una unidad a tu recorrido
                'U'->{ recorrido++ } // cada que subes se suma una unidad a tu recorrido
            }
            if(arraySecuencia[i]== 'U' && recorrido==0){
                // Si el recorrido vuelve a 0 y
                // en este caso venimos subiendo es decir el ultimo movimiento fue U
                // se considera que el numero de U y D son iguales y que salimos de un valle
                nValles++
            }
        }

        return nValles

    }

}