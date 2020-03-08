package com.onefootball.util

import android.content.Context
import com.onefootball.model.News
import org.json.JSONObject
import java.nio.charset.Charset

object NewsProvider {
    fun provideNewsFromFile(context: Context, fileName: String = DEFAULT_FILE_NAME): List<News> {
        val inputStream = context.assets.open(fileName)
        val size = inputStream.available()
        val buffer = ByteArray(size)
        inputStream.read(buffer)
        inputStream.close()
        val jsonString = buffer.toString(Charset.defaultCharset())
        return parseJsonString(jsonString)
    }

    private fun parseJsonString(jsonString: String): List<News> {
        val mainObject = JSONObject(jsonString)
        val newsItems = mutableListOf<News>()
        val newsArray = mainObject.getJSONArray(JSON_NEWS)
        newsArray.forEach { newsObject ->
            val title = newsObject.getString(JSON_TITLE)
            val imageUrl = newsObject.getString(JSON_IMAGE_URL)
            val resourceName = newsObject.getString(JSON_RESOURCE_NAME)
            val resourceUrl = newsObject.getString(JSON_RESOURCE_URL)
            val newsLink = newsObject.getString(JSON_NEWS_LINK)

            newsItems.add(
                News(
                    title = title,
                    imageUri = imageUrl,
                    resourceName = resourceName,
                    resourceUrl = resourceUrl,
                    newsLink = newsLink
                )
            )
        }
        return newsItems
    }

    private const val DEFAULT_FILE_NAME = "news.json"
    private const val JSON_NEWS = "news"
    private const val JSON_TITLE = "title"
    private const val JSON_IMAGE_URL = "image_url"
    private const val JSON_RESOURCE_NAME = "resource_name"
    private const val JSON_RESOURCE_URL = "resource_url"
    private const val JSON_NEWS_LINK = "news_link"
}