package com.ezatpanah.databinding_youtube.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.ezatpanah.databinding_youtube.adapter.NewsAdapter
import com.ezatpanah.databinding_youtube.databinding.ActivityMainBinding
import com.ezatpanah.databinding_youtube.utils.DataStatus
import com.ezatpanah.databinding_youtube.utils.initRecycler
import com.ezatpanah.databinding_youtube.utils.isVisible
import com.ezatpanah.databinding_youtube.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var newsAdapter: NewsAdapter
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()

        lifecycleScope.launch {
            binding.apply {
                viewModel.getTopHeadlineNews()
                viewModel.newsList.observe(this@MainActivity) { data ->
                    when (data.status) {
                        DataStatus.Status.LOADING -> {
                            pbLoading.isVisible(true, rvLastNews)
                        }
                        DataStatus.Status.SUCCESS -> {
                            pbLoading.isVisible(false, rvLastNews)
                            newsAdapter.setData(data.data!!)
                            newsAdapter.setOnItemClickListener {
                                val uri = Uri.parse(it.url)
                                val intent = Intent(Intent.ACTION_VIEW, uri)
                                this@MainActivity.startActivity(intent)
                            }
                        }
                        DataStatus.Status.ERROR -> {
                            Toast.makeText(this@MainActivity, "Something went wrong!", Toast.LENGTH_LONG).show()
                        }
                    }
                }
            }
        }
    }

    private fun setupRecyclerView() {
        binding.rvLastNews.initRecycler(LinearLayoutManager(this@MainActivity), newsAdapter)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}