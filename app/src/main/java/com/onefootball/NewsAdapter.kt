package com.onefootball

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.onefootball.databinding.NewsItemBinding
import com.onefootball.model.News

class NewsAdapter : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    private val newsItems = ArrayList<News>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding =
            DataBindingUtil.inflate<NewsItemBinding>(inflater, R.layout.news_item, parent, false)
        return NewsViewHolder(binding.root)
    }

    override fun getItemCount() = newsItems.size

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.onBind(newsItems[position])
    }

    fun setNewsItems(newListOfNewsItems: List<News>) {
        newsItems.clear()
        newsItems.addAll(newListOfNewsItems)
        notifyDataSetChanged()
    }

    class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = DataBindingUtil.getBinding<NewsItemBinding>(itemView)

        fun onBind(item: News) {
            if (binding != null) {
                with(binding) {
                    newsTitle.text = item.title
                    newsView.load(url = item.imageUri)
                    resourceIcon.load(url = item.resourceUrl)
                    resourceName.text = item.resourceName
                    root.setOnClickListener {
                        it.context.startActivity(
                            Intent(Intent.ACTION_VIEW, Uri.parse(item.newsLink))
                        )
                    }
                }
            }
        }
    }
}