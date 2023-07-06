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
            val textForFirstNumber = binding.EtFirstNumber.text.toString()
            val textForSecondNumber = binding.EtSecondNumber.text.toString()
            val btnOperationIntent = Intent(this, MainActivity::class.java)
            if(btnTextFromIntent=="DIV" && textForSecondNumber.toInt()==0){
                Toast.makeText(this,getString(R.string.divide_by_zero_error),Toast.LENGTH_SHORT).show()
            }
            else if (textForFirstNumber.isNotEmpty() && textForSecondNumber.isNotEmpty()) {
                btnOperationIntent.putExtra("firstNumber", textForFirstNumber.toInt())
                btnOperationIntent.putExtra("secondNumber", textForSecondNumber.toInt())
                btnOperationIntent.putExtra("operationName", btnTextFromIntent)
                setResult(RESULT_OK, btnOperationIntent)
                finish()
            }
            else {
                Toast.makeText(this,getString(R.string.enter_both_numbers_warning), Toast.LENGTH_SHORT).show()
            }
        }
    }
}