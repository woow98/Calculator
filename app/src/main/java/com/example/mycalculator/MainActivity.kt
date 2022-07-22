package com.example.mycalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.lang.NumberFormatException

class MainActivity : AppCompatActivity() {
    var resultField: TextView?=null
    var operationField: TextView?=null
    var firstField: TextView?=null
    var operand:Double?=null
    var lastOper:String?="="

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<TextView>(R.id.btn_AC).setOnClickListener {
            findViewById<TextView>(R.id.operation_panel).text = ""
            findViewById<TextView>(R.id.result_panel).text = ""
        }
        resultField=findViewById(R.id.result_panel)
        operationField=findViewById(R.id.operation_panel)
        firstField=findViewById(R.id.first_panel)
    }

    fun onNumberClick(view: View){

        val button = view as Button
        firstField!!.append(button.text)
        if(lastOper == "=" && operand!=null){
            operand = null;
        }
    }
    fun onOperationClick (view: View){
        val button = view as Button
        var number = firstField!!.text.toString()
        var op = button.text.toString()
        val text = "Введите следущее слагаемое!"
        val duration = Toast.LENGTH_SHORT

        val toast = Toast.makeText(applicationContext, text, duration)
        toast.show()
        if(number.length >0){
            number = number.replace(',','.')
            try{
                performOperation(java.lang.Double.valueOf(number),op)
                toast.show()

            } catch(e: NumberFormatException){
                firstField!!.setText("")
            }
        }
        if(op=="="){
            lastOper="="
        }
        else
            lastOper =op
        operationField!!.setText(lastOper)
    }
    private fun performOperation(number:Double, operation:String){

        if(operand==null){
            operand = number
        }
        else{
            if(lastOper=="=") {
                lastOper = operation
            }
            when (lastOper) {
                "=" -> operand = number
                "/" -> if (number == 0.0) {
                    operand = 0.0
                } else {
                    operand = operand!! / number
                }
                "*"-> operand = operand!!*number
                "+"->operand = operand!!+number
                "-"->operand = operand!!-number
            }
        }
        resultField!!.setText(operand.toString())
        firstField!!.setText("")
    }
}