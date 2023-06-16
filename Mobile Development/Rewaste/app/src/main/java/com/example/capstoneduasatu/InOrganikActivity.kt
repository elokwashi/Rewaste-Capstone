package com.example.capstoneduasatu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.capstoneduasatu.adapter.SearchAdapter
import com.example.capstoneduasatu.databinding.ActivityInOrganikBinding
import com.example.capstoneduasatu.response.DataItem
import com.example.capstoneduasatu.viewmodel.InOrganikViewModel
import com.example.capstoneduasatu.viewmodel.ViewModelFactory

class InOrganikActivity : AppCompatActivity() {

    private lateinit var binding: ActivityInOrganikBinding
    private lateinit var viewModelFactory: ViewModelFactory
    private var token = ""
    private val viewModel: InOrganikViewModel by viewModels { viewModelFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInOrganikBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        viewModelFactory=ViewModelFactory.getInstance(this)

        val layoutManager = LinearLayoutManager(this)
        binding.rvArticle.layoutManager = layoutManager
        val itemDecoration = DividerItemDecoration(this, layoutManager.orientation)
        binding.rvArticle.addItemDecoration(itemDecoration)

        viewModel.checkToken().observe(this) {
            token = it.token
        }

        viewModel.article.observe(this){
            searchArticle(it)
        }
        viewModel.isLoading.observe(this) { isLoading ->
            showLoading(isLoading)
        }

    }

    private fun searchArticle(item: List<DataItem>) {
        val adapter = SearchAdapter(item)
        binding.rvArticle.adapter = adapter
    }
    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

}