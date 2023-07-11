package com.example.calculator

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import com.example.calculator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val fetchResultFromSecondActivityLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                binding.tvResult.visibility = View.VISIBLE
                binding.btnReset.visibility = View.VISIBLE
                binding.btnAdd.visibility = View.GONE
                binding.btnSub.visibility = View.GONE
                binding.btnMul.visibility = View.GONE
                binding.btnDiv.visibility = View.GONE
                val intentForResult: Intent? = result.data
                val firstNumberText = intentForResult?.getStringExtra("firstNumber")
                val secondNumberText = intentForResult?.getStringExtra("secondNumber")
                val operationName = intentForResult?.getStringExtra("operationName")
                if (firstNumberText != null && secondNumberText != null) {
                    if (firstNumberText.contains(".")||secondNumberText.contains(".")) {
                        val firstNumber=firstNumberText.toDouble()
                        val secondNumber=secondNumberText.toDouble()
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
                    else{
                        val firstNumber=firstNumberText.toInt()
                        val secondNumber=secondNumberText.toInt()
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
        binding.btnReset.setOnClickListener {
            binding.tvResult.visibility = View.GONE
            binding.btnReset.visibility = View.GONE
            binding.btnAdd.visibility = View.VISIBLE
            binding.btnSub.visibility = View.VISIBLE
            binding.btnMul.visibility = View.VISIBLE
            binding.btnDiv.visibility = View.VISIBLE
            binding.tvResult.text = null

        }
        if (savedInstanceState?.getString("Result")?.isNotEmpty() != null) {
            binding.tvResult.visibility = View.VISIBLE
            binding.btnReset.visibility = View.VISIBLE
            binding.btnAdd.visibility = View.GONE
            binding.btnSub.visibility = View.GONE
            binding.btnMul.visibility = View.GONE
            binding.btnDiv.visibility = View.GONE
            binding.tvResult.text = savedInstanceState.getString("Result")
        }

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        if (binding.tvResult.text.isNotEmpty()) {
            outState.putString("Result", binding.tvResult.text.toString())
        }
    }

    private fun intentGenerator(btnString: String) {
        val btnForSecondActivityIntent = Intent(this, SecondActivity::class.java)
        btnForSecondActivityIntent.putExtra("operationName", btnString)
        fetchResultFromSecondActivityLauncher.launch(btnForSecondActivityIntent)
    }

}
