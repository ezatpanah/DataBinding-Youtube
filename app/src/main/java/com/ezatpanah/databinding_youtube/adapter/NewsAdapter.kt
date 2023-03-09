package com.ezatpanah.databinding_youtube.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.ezatpanah.databinding_youtube.databinding.ItemNewsBinding
import com.ezatpanah.databinding_youtube.response.ResponseTopHeadline
import javax.inject.Inject

class NewsAdapter @Inject constructor() : RecyclerView.Adapter<NewsAdapter.MyViewHolder>() {

    private var news = emptyList<ResponseTopHeadline.Article>()
    lateinit var binding: ItemNewsBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        binding = ItemNewsBinding.inflate(inflater, parent, false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int = news.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(news[position])
    }

    inner class MyViewHolder(private val binding: ItemNewsBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(article: ResponseTopHeadline.Article) {
            binding.article = article
            binding.executePendingBindings()
            binding.root.setOnClickListener {
                onItemClickListener?.let {
                    it(article)
                }
            }
        }
    }

    private var onItemClickListener: ((ResponseTopHeadline.Article) -> Unit)? = null

    fun setOnItemClickListener(listener: (ResponseTopHeadline.Article) -> Unit) {
        onItemClickListener = listener
    }

    fun setData(newData: ResponseTopHeadline) {
        val newsDiffUtil = NewsDiffUtil(news, newData.articles)
        val diffUtils = DiffUtil.calculateDiff(newsDiffUtil)
        news = newData.articles
        diffUtils.dispatchUpdatesTo(this)
    }

}