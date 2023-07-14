package com.example.calculator

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
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
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title=btnTextFromIntent
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
                            resultString = (firstNumber + secondNumber).toString()
                            setResult(resultString)
                        }

                        "SUB" -> {
                            resultString = (firstNumber - secondNumber).toString()
                            setResult(resultString)
                        }

                        "MUL" -> {
                            resultString = (firstNumber * secondNumber).toString()
                            setResult(resultString)

                        }

                        "DIV" -> {
                            resultString = (firstNumber / secondNumber).toString()
                            setResult(resultString)
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

    private fun setResult(resultString: String) {
        val btnOperationIntent = Intent(this, MainActivity::class.java)
        btnOperationIntent.putExtra("Result", resultString)
        setResult(RESULT_OK, btnOperationIntent)
        finish()
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
