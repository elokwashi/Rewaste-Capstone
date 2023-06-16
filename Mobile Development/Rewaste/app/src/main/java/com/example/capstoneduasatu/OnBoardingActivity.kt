package com.example.capstoneduasatu

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.capstoneduasatu.databinding.ActivityLoginBinding
import com.example.capstoneduasatu.databinding.ActivityOnBoardingBinding
import com.example.capstoneduasatu.viewmodel.LoginViewModel
import com.example.capstoneduasatu.viewmodel.ViewModelFactory

class OnBoardingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOnBoardingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnBoardingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        binding.loginButtonOB.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }

        binding.buttonDaftarOn.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }
}