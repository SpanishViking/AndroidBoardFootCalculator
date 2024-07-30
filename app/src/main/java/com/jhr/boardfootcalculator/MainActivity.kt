package com.jhr.boardfootcalculator

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.text.*

class MainActivity : AppCompatActivity() {
    lateinit var btnCalculate : Button
    lateinit var btnClear : Button
    lateinit var et_length : EditText
    lateinit var et_width : EditText
    lateinit var tv_result : TextView
    lateinit var s_thickness : Spinner

    var thickness : Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        btnCalculate = findViewById(R.id.btn_calculate)
        btnClear = findViewById(R.id.btn_clear)
        s_thickness = findViewById(R.id.s_thickness)
        et_length = findViewById(R.id.et_length)
        et_width = findViewById(R.id.et_width)
        tv_result = findViewById(R.id.tv_result)

        //btnCalculate.setOnClickListener(this)
        //btnClear.setOnClickListener(this)

        // Create the ArrayAdapter using the thickness string array with a default spinner layout.
        ArrayAdapter.createFromResource(this, R.array.thickness_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears.
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner.
            this.s_thickness.adapter = adapter
        }
        s_thickness.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                when(position){
                    0 -> thickness = 0.0
                    1 -> thickness = 1.0
                    2 -> thickness = 1.25
                    3 -> thickness = 1.5
                    4 -> thickness = 1.75
                    5 -> thickness = 2.0
                    6 -> thickness = 2.25
                    7 -> thickness = 2.5
                    8 -> thickness = 2.75
                    9 -> thickness = 3.0
                    10 -> thickness = 3.25
                    11 -> thickness = 3.5
                    12 -> thickness = 3.75
                    13 -> thickness = 4.0
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Nothing to implement as of now
            }
        }

        btnClear.setOnClickListener{
            et_width.text.clear()
            et_length.text.clear()
            s_thickness.setSelection(0)
            tv_result.setText("")
        }

        btnCalculate.setOnClickListener{
            if ((et_width.text.toString().trim().isNullOrBlank() || et_length.text.toString().trim().isNullOrBlank()) || thickness == 0.0 ) {
                Toast.makeText(applicationContext, "All dimensions should be set or non-zero! ", Toast.LENGTH_SHORT).show()
            }else{
                val w = et_width.text.toString().toDouble()
                val l = et_length.text.toString().toDouble()
                val result = (thickness * w * l)/144.0
                val stringResult = result.format(2).toString() + " bf"
                tv_result.text = stringResult
            }
        }
    }

    private fun Double.format(digits: Int) = "%.${digits}f".format(this)
}