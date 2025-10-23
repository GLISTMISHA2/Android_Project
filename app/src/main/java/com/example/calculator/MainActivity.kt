package com.example.calculator
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.graphics.Color
class MainActivity : AppCompatActivity() {
    var num1: String = ""
    var num2: String = ""
    var operator: String = ""
    lateinit var result: TextView
    var flag_comma1: Boolean = true
    var flag_comma2: Boolean = true

    var Colorr = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        result = findViewById(R.id.result)

        result.setOnClickListener {
            if (Colorr == 0) {
                result.setTextColor(Color.rgb(255, 0, 0))
                Colorr = 1
            } else if (Colorr == 1) {
                result.setTextColor(Color.rgb(0, 255, 0))
                Colorr = 2
            } else {
                result.setTextColor(Color.rgb(0, 0, 0))
                Colorr = 0
            }
        }
        val button_0 = findViewById<Button>(R.id.button_0)
        val button_1 = findViewById<Button>(R.id.button_1)
        val button_2 = findViewById<Button>(R.id.button_2)
        val button_3 = findViewById<Button>(R.id.button_3)
        val button_4 = findViewById<Button>(R.id.button_4)
        val button_5 = findViewById<Button>(R.id.button_5)
        val button_6 = findViewById<Button>(R.id.button_6)
        val button_7 = findViewById<Button>(R.id.button_7)
        val button_8 = findViewById<Button>(R.id.button_8)
        val button_9 = findViewById<Button>(R.id.button_9)
        val button_plus = findViewById<Button>(R.id.button_plus)
        val button_minus = findViewById<Button>(R.id.button_minus)
        val button_umn = findViewById<Button>(R.id.button_umn)
        val button_del = findViewById<Button>(R.id.button_del)
        val button_ravn = findViewById<Button>(R.id.button_ravn)
        val button_clear = findViewById<Button>(R.id.button_clear)
        val button_comma = findViewById<Button>(R.id.button_comma)

        button_0.setOnClickListener { addnum("0") }
        button_1.setOnClickListener { addnum("1") }
        button_2.setOnClickListener { addnum("2") }
        button_3.setOnClickListener { addnum("3") }
        button_4.setOnClickListener { addnum("4") }
        button_5.setOnClickListener { addnum("5") }
        button_6.setOnClickListener { addnum("6") }
        button_7.setOnClickListener { addnum("7") }
        button_8.setOnClickListener { addnum("8") }
        button_9.setOnClickListener { addnum("9") }
        button_comma.setOnClickListener { addnum(".") }
        button_plus.setOnClickListener { addOperator("+") }
        button_minus.setOnClickListener { addOperator("-") }
        button_umn.setOnClickListener { addOperator("×") }
        button_del.setOnClickListener { addOperator("÷") }

        button_ravn.setOnClickListener { calculate() }
        button_clear.setOnClickListener { clear() }
    }

    fun addnum(dig: String) {
        if (operator.isEmpty()) {
            if (dig == "." && !flag_comma1) {
            } else {
                if (dig == ".") {
                    flag_comma1 = false
                }
                num1 += dig
                result.text = num1
            }
        } else {
            if (dig == "." && !flag_comma2) {
            } else {
                if (dig == ".") {
                    flag_comma2 = false
                }
                num2 += dig
                result.text = num1 + operator + num2
            }
        }
    }

    fun addOperator(oper: String) {
        if (num1.isNotEmpty()) {
            operator = oper
            result.text = num1 + operator
            num2 = ""
        }
    }

    fun calculate() {
        if (num1.isNotEmpty() && operator.isNotEmpty() && num2.isNotEmpty()) {
            val number1 = num1.toDouble()
            val number2 = num2.toDouble()
            var res: Double = 0.0
            var resText: String = ""

            if (operator == "÷" && number2 == 0.0) {
                result.text = "Ошибка"
                num1 = ""
                num2 = ""
                operator = ""
                flag_comma1 = true
                flag_comma2 = true
                return
            }

            if (operator == "+") {
                res = (number1 + number2)
            } else if (operator == "-") {
                res = (number1 - number2)
            } else if (operator == "×") {
                res = (number1 * number2)
            } else {
                res = (number1 / number2)
            }

            if (res % 1 == 0.0) {
                resText = res.toInt().toString()
            } else {
                resText = res.toString()
            }

            result.text = resText
            num1 = resText
            num2 = ""
            operator = ""
        }
    }
    fun clear() {
        num1 = ""
        num2 = ""
        flag_comma1 = true
        flag_comma2 = true
        operator = ""
        result.text = ""
        Colorr = 0
    }
}