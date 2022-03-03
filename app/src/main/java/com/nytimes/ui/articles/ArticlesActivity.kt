package com.nytimes.ui.articles

import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nytimes.R
import com.nytimes.api.RetrofitService
import com.nytimes.data.Article
import com.nytimes.databinding.ActivityArticlesBinding
import com.nytimes.di.ViewModelFactory
import com.nytimes.repository.ArticlesRepository


class ArticlesActivity : AppCompatActivity() {

    private lateinit var recyclerAdapter: ArticlesAdapter
    private lateinit var viewModel: ArticlesViewModel
    private val articles = mutableListOf<Article>()
    private val retrofitService = RetrofitService.getInstance()
    private lateinit var binding: ActivityArticlesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityArticlesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.sectionToolbar.myToolbar)

        viewModel =
            ViewModelProvider(this, ViewModelFactory(ArticlesRepository(retrofitService))).get(
                ArticlesViewModel::class.java
            )

        initRecycler(binding.articlesRecycler)

        viewModel.articles.observe(this, { articles ->
            if (articles == null) return@observe
            this.articles.clear()
            this.articles.addAll(articles)
            recyclerAdapter.notifyDataSetChanged()
            binding.progress.visibility = View.GONE
        })

        viewModel.errorMessage.observe(this, { msg ->
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
            binding.progress.visibility = View.GONE
        })


        binding.progress.visibility = View.VISIBLE
        viewModel.getResults()


    }

    private fun initRecycler(recyclerView: RecyclerView) {
        recyclerView.setHasFixedSize(true)
        val gridLayoutManager =
            GridLayoutManager(this, 1, GridLayoutManager.VERTICAL, false)
        recyclerView.layoutManager = gridLayoutManager
        recyclerAdapter = ArticlesAdapter(articles)
        recyclerView.adapter = recyclerAdapter
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }


}