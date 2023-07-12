package com.example.calculator

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.calculator.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySecondBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val btnTextFromIntent = intent.getStringExtra("operationName")
        binding.btnOperation.text = btnTextFromIntent

        binding.btnOperation.setOnClickListener {
            val textForFirstNumber = binding.EtFirstNumber.text
            val textForSecondNumber = binding.EtSecondNumber.text
            val resultString: String
            if (textForFirstNumber.isNotEmpty() && textForSecondNumber.isNotEmpty()) {
                val firstNumber = textForFirstNumber.toString().toInt()
                val secondNumber = textForSecondNumber.toString().toInt()
                if (secondNumber == 0 && btnTextFromIntent == "DIV") {
                    Toast.makeText(
                        this,
                        getString(R.string.divide_by_zero_error),
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    when (btnTextFromIntent) {
                        "ADD" -> {
                            resultString = (firstNumber.plus(secondNumber).toString())
                            resultIntentGenerator(resultString)
                        }

                        "SUB" -> {
                            resultString = (firstNumber - secondNumber).toString()
                            resultIntentGenerator(resultString)
                        }

                        "MUL" -> {
                            resultString = (firstNumber * secondNumber).toString()
                            resultIntentGenerator(resultString)

                        }

                        "DIV" -> {
                            resultString = (firstNumber / secondNumber).toString()
                            resultIntentGenerator(resultString)
                        }
                    }
                }
            } else {
                Toast.makeText(
                    this,
                    getString(R.string.enter_both_numbers_warning),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun resultIntentGenerator(resultString: String) {
        val btnOperationIntent = Intent(this, MainActivity::class.java)
        btnOperationIntent.putExtra("Result", resultString)
        setResult(RESULT_OK, btnOperationIntent)
        finish()
    }
}
