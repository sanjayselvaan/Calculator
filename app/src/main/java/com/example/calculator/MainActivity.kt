package com.example.calculator

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import com.example.calculator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    private val fetchResultFromSecondActivityLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                binding.layout1.visibility= View.GONE
                binding.layout2.visibility=View.VISIBLE
                val intentForResult: Intent? = result.data
                val firstNumber = intentForResult?.getIntExtra("firstNumber", 0)
                val secondNumber = intentForResult?.getIntExtra("secondNumber", 0)
                val operationName = intentForResult?.getStringExtra("operationName")
                if (firstNumber != null && secondNumber != null) {
                    when (operationName) {
                        "ADD" -> {
                            binding.tvResult.text = (firstNumber.plus(secondNumber).toString())
                        }
                        "SUB" -> {
                            binding.tvResult.text = (firstNumber - secondNumber).toString()
                        }
                        "MUL" -> {
                                binding.tvResult.text = (firstNumber * secondNumber).toString()
                            }
                        "DIV" -> {
                            binding.tvResult.text = (firstNumber * secondNumber).toString()
                        }
                    }
                }
            }
            else {
                println("hellooooo")

            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnAdd.setOnClickListener {
            intentGenerator(binding.btnAdd.text.toString())
        }
        binding.btnSub.setOnClickListener {
            intentGenerator(binding.btnSub.text.toString())
        }
        binding.btnMul.setOnClickListener {
            intentGenerator(binding.btnMul.text.toString())
        }
        binding.btnDiv.setOnClickListener {
            intentGenerator(binding.btnDiv.text.toString())
        }
    }

    private fun intentGenerator(btnString: String) {
        val btnForSecondActivityIntent = Intent(this, SecondActivity::class.java)
        btnForSecondActivityIntent.putExtra("operationName", btnString)
        fetchResultFromSecondActivityLauncher.launch(btnForSecondActivityIntent)
    }
}