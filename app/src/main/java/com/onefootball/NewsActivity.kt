package com.onefootball

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.onefootball.model.News
import org.json.JSONArray
import org.json.JSONObject
import java.nio.charset.Charset

class NewsActivity : AppCompatActivity() {

    private var jsonString : String? = null
    private lateinit var recyclerView: RecyclerView
    private lateinit var newsAdapter: NewsAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news)
        recyclerView = findViewById(R.id.newsRecyclerView)
        newsAdapter = NewsAdapter()
        with(recyclerView) {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(this@NewsActivity)
        }
    }

    override fun onResume() {
        super.onResume()
        val inputStream = assets.open("news.json")
        val size = inputStream.available()
        val buffer = ByteArray(size)
        inputStream.read(buffer)
        inputStream.close()
        jsonString = buffer.toString(Charset.defaultCharset())

        val items = parseJsonString(jsonString!!)

        newsAdapter.setNewsItems(items)
    }

    private fun parseJsonString(jsonString: String): List<News> {
        val mainObject = JSONObject(jsonString)
        val newsItems = mutableListOf<News>()
        val newsArray = mainObject.getJSONArray("news")
        newsArray.forEach {
            newsObject ->
            val title = newsObject.getString("title")
            val imageUrl = newsObject.getString("image_url")
            val resourceName = newsObject.getString("resource_name")
            val resourceUrl = newsObject.getString("resource_url")
            val newsLink = newsObject.getString("news_link")

            newsItems.add(News(title = title, imageUri = imageUrl, resourceName = resourceName, resourceUrl = resourceUrl, newsLink = newsLink))
        }
        return newsItems
    }
}


class NewsAdapter: RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    private val newsItems = ArrayList<News>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.news_item, parent, false)
        return NewsViewHolder(view)
    }

    override fun getItemCount() = newsItems.size

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val news = newsItems[position]

        holder.titleView.text = news.title
        holder.newsView.load(url = news.imageUri)
        holder.resourceImage.load(url = news.resourceUrl)
        holder.resourceName.text = news.resourceName
        holder.itemView.setOnClickListener {
            it.context.startActivity(
                Intent(Intent.ACTION_VIEW, Uri.parse(news.newsLink))
            )
        }
    }

    fun setNewsItems(newListOfNewsItems: List<News>) {
        newsItems.clear()
        newsItems.addAll(newListOfNewsItems)
        notifyDataSetChanged()
    }

    class NewsViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        var titleView: TextView = itemView.findViewById(R.id.news_title)
        var newsView: ImageView = itemView.findViewById(R.id.news_view)
        var resourceImage: ImageView = itemView.findViewById(R.id.resource_icon)
        var resourceName: TextView = itemView.findViewById(R.id.resource_name)
    }
}


fun JSONArray.forEach(jsonObject: (JSONObject) -> Unit) {
    for (index in 0 until this.length()) jsonObject(this[index] as JSONObject)
}