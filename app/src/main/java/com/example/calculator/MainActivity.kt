package com.example.calculator

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.view.View.OnClickListener
import androidx.activity.result.contract.ActivityResultContracts
import com.example.calculator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), OnClickListener {
    private lateinit var binding: ActivityMainBinding
    private val fetchResultFromSecondActivityLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                binding.operationsLayout.visibility = View.GONE
                binding.resultLayout.visibility = View.VISIBLE
                val intentForResult: Intent? = result.data
                val resultText = intentForResult?.getStringExtra("Result")
                binding.tvResult.text = resultText
                supportActionBar?.setDisplayHomeAsUpEnabled(true)
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnAdd.text = Operation.ADD.toString()
        binding.btnSub.text = Operation.SUB.toString()
        binding.btnMul.text = Operation.MUL.toString()
        binding.btnDiv.text = Operation.DIV.toString()
        binding.btnReset.text = Operation.RESET.toString()
        binding.btnAdd.setOnClickListener(this)
        binding.btnSub.setOnClickListener(this)
        binding.btnMul.setOnClickListener(this)
        binding.btnDiv.setOnClickListener(this)
        binding.btnReset.setOnClickListener(this)
        if (savedInstanceState?.getString("Result")?.isNotEmpty() != null) {
            binding.operationsLayout.visibility = View.GONE
            binding.resultLayout.visibility = View.VISIBLE
            binding.tvResult.text = savedInstanceState.getString("Result")
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
    }
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        if (binding.tvResult.text.isNotEmpty()) {
            outState.putString("Result", binding.tvResult.text.toString())
        }
    }


    private fun calculateOperation(btnString: String) {
        val btnForSecondActivityIntent = Intent(this, SecondActivity::class.java)
        btnForSecondActivityIntent.putExtra("operationName", btnString)
        fetchResultFromSecondActivityLauncher.launch(btnForSecondActivityIntent)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                binding.btnReset.performClick()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onClick(v: View?) {
        when (v) {
            binding.btnReset -> {
                binding.resultLayout.visibility = View.GONE
                binding.operationsLayout.visibility = View.VISIBLE
                binding.tvResult.text = null
                supportActionBar?.setDisplayHomeAsUpEnabled(false)
            }
            binding.btnAdd->{
                calculateOperation(binding.btnAdd.text.toString())
            }
            binding.btnSub->{
                calculateOperation(binding.btnSub.text.toString())
            }
            binding.btnMul->{
                calculateOperation(binding.btnMul.text.toString())
            }
            binding.btnDiv->{
                calculateOperation(binding.btnDiv.text.toString())
            }
        }
    }
    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        if(binding.resultLayout.visibility == View.VISIBLE){
            binding.btnReset.performClick()
            Log.d("test","inside if")

        }
        else{
            Log.d("test","inside else")
            super.onBackPressed()
        }
    }
}

