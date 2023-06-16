package com.example.capstoneduasatu.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.capstoneduasatu.DetailActivity
import com.example.capstoneduasatu.DetailActivity.Companion.EXTRA_DATA
import com.example.capstoneduasatu.databinding.ItemRowArticleBinding
import com.example.capstoneduasatu.response.DataItem

class SearchAdapter(private val articles: List<DataItem>) :
    RecyclerView.Adapter<SearchAdapter.ListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding =
            ItemRowArticleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val article = articles[position]
        holder.bind(article)
    }

    override fun getItemCount(): Int {
        return articles.size
    }

    class ListViewHolder(private val binding: ItemRowArticleBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(article: DataItem) {
            binding.apply {
                judultv.text = article.name
                deskirpsinya.text = article.description
                Glide.with(itemView.context)
                    .load(article.imageUrl)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(gambar)

                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailActivity::class.java)
                    intent.putExtra(EXTRA_DATA, article)
                    itemView.context.startActivity(intent)
                }
            }
        }
    }
}







