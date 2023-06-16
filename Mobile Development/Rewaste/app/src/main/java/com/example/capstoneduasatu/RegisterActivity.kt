package com.example.capstoneduasatu

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import com.example.capstoneduasatu.databinding.ActivityRegisterBinding
import com.example.capstoneduasatu.helper.ResultState
import com.example.capstoneduasatu.viewmodel.RegisterViewModel
import com.example.capstoneduasatu.viewmodel.ViewModelFactory

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private lateinit var viewModelFactory: ViewModelFactory
    private val registerViewModel: RegisterViewModel by viewModels{viewModelFactory}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModelFactory = ViewModelFactory.getInstance(this)
        supportActionBar?.hide()

        pushRegister()

        binding.toLogin.setOnClickListener {
            intentToLogin()
        }
    }

    private fun pushRegister(){
        binding.buttonDaftar.setOnClickListener {
            val name = binding.editTextTextPersonName.text.toString()
            val email = binding.emailedRegister.text.toString()
            val password = binding.passwordedRegister.text.toString()
            if (name.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty()){
                val result = registerViewModel.register(name, email, password)
                result.observe(this){
                    when(it){
                        is ResultState.Error -> {
                            binding.progressBar2.visibility = View.INVISIBLE
                            val error = it.error
                            Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
                        }
                        is ResultState.Success -> {
                            binding.progressBar2.visibility = View.INVISIBLE
                            Toast.makeText(this, "register_success", Toast.LENGTH_SHORT).show()
                            intentToLogin()
                        }
                        is ResultState.Loading -> {
                            binding.progressBar2.visibility = View.VISIBLE
                        }
                    }
                }
            }
        }
    }

    private fun intentToLogin(){
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }
}