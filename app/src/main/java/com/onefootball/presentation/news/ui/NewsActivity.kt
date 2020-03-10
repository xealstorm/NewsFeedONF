package com.onefootball.presentation.news.ui

import android.content.res.Configuration
import android.os.Bundle
import android.os.PersistableBundle
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.onefootball.R
import com.onefootball.databinding.ActivityNewsBinding
import com.onefootball.di.activity.ActivityComponent
import com.onefootball.di.news.NewsModule
import com.onefootball.presentation.base.ui.BaseActivity
import com.onefootball.presentation.news.model.NewsItem
import com.onefootball.presentation.news.presenter.NewsPresenter
import javax.inject.Inject

class NewsActivity : BaseActivity(), NewsView {

    private lateinit var binding: ActivityNewsBinding
    @Inject
    internal lateinit var newsAdapter: NewsAdapter
    @Inject
    internal lateinit var newsPresenter: NewsPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(
            this,
            R.layout.activity_news
        )
        newsPresenter.setView(this@NewsActivity)
        with(binding.newsRecyclerView) {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(this@NewsActivity)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
    }

    override fun onResume() {
        super.onResume()
        newsPresenter.provideNews()
    }

    override fun doInjections(activityComponent: ActivityComponent?) {
        activityComponent?.plus(NewsModule())?.injectTo(this)
    }

    override fun updateNewsWithList(newsList: List<NewsItem>) {
        newsAdapter.setNewsItems(newsList)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putSerializable(ITEMS_BUNDLE_KEY, newsAdapter.getItems())
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        val items = savedInstanceState.getSerializable(ITEMS_BUNDLE_KEY) as List<NewsItem>
        newsAdapter.setNewsItems(items)
    }

    override fun hasItems() = newsAdapter.itemCount > 0

    companion object{
        private const val ITEMS_BUNDLE_KEY = "news_items"
    }
}


