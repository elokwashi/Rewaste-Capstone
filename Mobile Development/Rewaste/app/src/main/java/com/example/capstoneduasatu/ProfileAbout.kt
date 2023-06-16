package com.example.capstoneduasatu

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.capstoneduasatu.databinding.ActivityDetailBinding
import com.example.capstoneduasatu.databinding.ActivityProfileAboutBinding
import com.example.capstoneduasatu.databinding.ActivityRegisterBinding
import com.example.capstoneduasatu.fragments.ProfileFragment

class ProfileAbout : AppCompatActivity() {
    private lateinit var binding: ActivityProfileAboutBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileAboutBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()


    }

}