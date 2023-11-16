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

    lateinit var buttonReloj: Button
    lateinit var textViewRR : TextView


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

        buttonReloj = findViewById(R.id.buttonReloj)
        textViewRR = findViewById(R.id.textViewRR)

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

        buttonReloj.setOnClickListener { textViewRR.text = "Suma mayor "+relojs() }



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
    fun relojs (): Int{
        val matriz = arrayOf(
            intArrayOf(-9,-9,-9, 1, 1, 1),
            intArrayOf( 0,-9, 0, 4, 3, 2),
            intArrayOf(-9,-9,-9, 1, 2, 3),
            intArrayOf( 0, 0, 8, 6, 6, 0),
            intArrayOf( 0, 0, 0,-2, 0, 0),
            intArrayOf( 0, 0, 1, 2, 4, 0),
            )
        /***
         * En la matriz un reloj se representa por :

         a b c
           d
         e f g

         traducido a indices de una matriz seria

         m[i][j]  m[i][j+1]  m[i][j+2]
                  m[i][j+1]
        m[i+2][j] m[i+2][j+1] m[i+2][j+2]

        Así se recorreria la matriz haciendo un recorrido
        lineal lo cual quiere decir que deberia haber un salto en el indice de i,
         este salto se dara cuando el indice j en su valor pase 5,
        lo cual indica que debemos considerar j+2 para no salir de los indices
        y para calcular el ultimo salto en el indice i que se puede realizar nos guiaremos de i+2
         */
        var mayor=-63
        /** como se busca el número mayor tomamos el menor para comparar
         como en este caso se pueden número s negativos el menor no puede ser 0
         el numero menos es negativo de la suma de 7 numeros negativos -9*/

        for (i in matriz.indices) {
            if(i+2>5)break  // si ya no hay relojs
            for (j in 0 until matriz[i].size) {
                if(j+2>5)break // para hacer un salto de indice y buscar mas relojs
                val a = matriz[i][j]
                val b = matriz[i][j+1]
                val c = matriz[i][j+2]
                val d = matriz[i+1][j+1]
                val e = matriz[i+2][j]
                val f = matriz[i+2][j+1]
                val g = matriz[i+2][j+2]

                val sumaReloj = a+b+c+d+e+f+g

                if(sumaReloj>mayor){
                    mayor=sumaReloj
                }

            }
        }
        return mayor
    }







}