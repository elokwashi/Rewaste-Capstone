package com.example.capstoneduasatu

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import com.example.capstoneduasatu.databinding.ActivityLoginBinding
import com.example.capstoneduasatu.helper.ResultState
import com.example.capstoneduasatu.helper.model.UserModel
import com.example.capstoneduasatu.viewmodel.LoginViewModel
import com.example.capstoneduasatu.viewmodel.ViewModelFactory

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var viewModelFactory: ViewModelFactory
    private val loginViewModel: LoginViewModel by viewModels { viewModelFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModelFactory = ViewModelFactory.getInstance(this)
        supportActionBar?.hide()

        pushLogin()

        binding.intentDaftar.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }

    }

    private fun pushLogin(){
        binding.loginButton.setOnClickListener {
            val email = binding.emailed.text.toString()
            val password = binding.password.text.toString()
            if (email.isNotEmpty() && password.isNotEmpty()) {
                val result = loginViewModel.login(email, password)
                result.observe(this) {
                    when (it) {
                        is ResultState.Loading -> {
                            binding.progressBar.visibility = View.VISIBLE
                        }
                        is ResultState.Error -> {
                            binding.progressBar.visibility = View.INVISIBLE
                            val error = it.error
                            Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
                        }
                        is ResultState.Success -> {
                            binding.progressBar.visibility = View.INVISIBLE
                            val data = it.data
                            loginViewModel.saveToken(
                                UserModel(
                                    data.data.name,
                                    AUTH_KEY + (data.data.token),
                                    true
                                )
                            )
                            loginViewModel.login()
                            val intent = Intent(this, MainActivity::class.java)
                            startActivity(intent)
                            finish()
                        }
                    }
                }
            }
        }
    }

    companion object {
        private const val AUTH_KEY = "Bearer "
    }

}