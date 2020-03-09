package com.onefootball.presentation.news.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.onefootball.R
import com.onefootball.databinding.ActivityNewsBinding
import com.onefootball.presentation.news.model.NewsItem
import com.onefootball.presentation.news.presenter.NewsPresenter

class NewsActivity : AppCompatActivity(), NewsView {

    private lateinit var binding: ActivityNewsBinding
    private lateinit var newsAdapter: NewsAdapter
    private lateinit var newsPresenter: NewsPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(
            this,
            R.layout.activity_news
        )
        newsPresenter = NewsPresenter().apply { setView(this@NewsActivity) }
        newsAdapter = NewsAdapter()
        with(binding.newsRecyclerView) {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(this@NewsActivity)
        }
    }

    override fun onResume() {
        super.onResume()
        newsPresenter.provideNews()
    }

    override fun updateNewsWithList(newsList: List<NewsItem>) {
        newsAdapter.setNewsItems(newsList)
    }
}


