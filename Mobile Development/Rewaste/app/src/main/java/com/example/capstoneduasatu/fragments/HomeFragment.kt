package com.example.capstoneduasatu.fragments

import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.capstoneduasatu.adapter.ArticleAdapter
import com.example.capstoneduasatu.databinding.FragmentHomeBinding
import com.example.capstoneduasatu.viewmodel.HomeViewModel
import com.example.capstoneduasatu.viewmodel.ViewModelFactory

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var articleAdapter: ArticleAdapter
    private lateinit var factory: ViewModelFactory
    private val homeViewModel: HomeViewModel by viewModels { factory }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        factory = ViewModelFactory.getInstance(binding.root.context)
        setupView(root.context)
        homeViewModel.getListArticle.observe(viewLifecycleOwner) {
            articleAdapter.submitData(lifecycle, it)
        }
        return root

    }

    private fun setupView(context: Context) {
        val storiesRv = binding.rvArticle

        if (context.resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            storiesRv.layoutManager = GridLayoutManager(context, 2)
        } else {
            storiesRv.layoutManager = LinearLayoutManager(context)
        }

        articleAdapter = ArticleAdapter()
        storiesRv.adapter = articleAdapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}