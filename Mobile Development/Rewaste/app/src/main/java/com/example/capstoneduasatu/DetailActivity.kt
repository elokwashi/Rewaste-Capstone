package com.example.capstoneduasatu

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.capstoneduasatu.databinding.ActivityDetailBinding
import com.example.capstoneduasatu.response.DataItem

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        detail()

        binding.fab.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }

        val tv: TextView = findViewById(R.id.deskripsiDetail)
        tv.movementMethod = ScrollingMovementMethod()

    }

    private fun detail(){
        val data = intent.getParcelableExtra<DataItem>(EXTRA_DATA) as DataItem
        binding.apply {
            judulDetail.text = data.name
            deskripsiDetail.text = data.description
            Glide.with(this@DetailActivity)
                .load(data.imageUrl)
                .fitCenter()
                .into(imageView3)
        }
    }

    companion object {
        const val EXTRA_DATA = "extra_data"
    }
}