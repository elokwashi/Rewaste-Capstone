package com.example.capstoneduasatu.fragments


import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.capstoneduasatu.adapter.ArticleAdapter
import com.example.capstoneduasatu.adapter.SearchAdapter
import com.example.capstoneduasatu.databinding.FragmentSearchBinding
import com.example.capstoneduasatu.response.DataItem
import com.example.capstoneduasatu.viewmodel.SearchViewModel
import com.example.capstoneduasatu.viewmodel.ViewModelFactory


class SearchFragment : Fragment() {
    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!
    private var token= ""
    private lateinit var factory: ViewModelFactory
    private val searchViewModel: SearchViewModel by viewModels { factory }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        val root: View = binding.root

        factory = ViewModelFactory.getInstance(binding.root.context)
        setupView(root.context)

        searchViewModel.checkToken().observe(this) {
            token = it.token
        }

        searchViewModel.article.observe(viewLifecycleOwner){
            searchArticle(it)
        }
        searchViewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            showLoading(isLoading)
        }

        binding?.apply {
            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener,android.widget.SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                showLoading(true)
                if (query != null) {
                    searchViewModel.findArticle(token,query)
                    searchView.clearFocus()
                }
                return true
                }
                override fun onQueryTextChange(newText: String?): Boolean {
                    showLoading(true)
                    if (newText != null) {
                        searchViewModel.findArticle(token,newText)
                    }
                return false
                }
            })
        }

        return root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    private fun searchArticle(item: List<DataItem>) {
        val adapter = SearchAdapter(item)
        binding.rvArticle.adapter = adapter
    }
    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    private fun setupView(context: Context) {
        val storiesRv = binding.rvArticle

        if (context.resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            storiesRv.layoutManager = GridLayoutManager(context, 2)
        } else {
            storiesRv.layoutManager = LinearLayoutManager(context)
        }

    }




}
