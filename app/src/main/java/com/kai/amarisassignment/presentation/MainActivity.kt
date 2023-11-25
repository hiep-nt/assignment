package com.kai.amarisassignment.presentation

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.core.widget.doOnTextChanged
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kai.amarisassignment.databinding.ActivityMainBinding
import com.kai.amarisassignment.extention.collectLatestLifecycleFlow
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val viewModel: MainViewModel by viewModels()

    private lateinit var adapter: MovieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initView()
        dataObserve()

        viewModel.loadMore()
    }

    private fun initView() {

        adapter = MovieAdapter()
        binding.rvMovie.layoutManager = GridLayoutManager(this, 2)
        binding.rvMovie.adapter = adapter

        binding.rvMovie.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val layoutManager = recyclerView.layoutManager as GridLayoutManager
                val visibleItemCount = layoutManager.childCount
                val totalItemCount = layoutManager.itemCount
                val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()

                if (visibleItemCount + firstVisibleItemPosition >= totalItemCount && firstVisibleItemPosition >= 0) {

                    viewModel.loadMore()
                }
            }
        })

        binding.edtSearch.doOnTextChanged { text, _, _, _ ->
            viewModel.updateSearchKeyword(text.toString().trim())
        }
    }

    private fun dataObserve() {

        collectLatestLifecycleFlow(viewModel.listMoviesFlow) {
            adapter.submitList(it)
        }

        collectLatestLifecycleFlow(viewModel.isLoading) {
            binding.loadingBar.isVisible = it
        }
    }

}