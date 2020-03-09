package com.onefootball.util

import android.content.res.AssetManager
import com.onefootball.model.News
import org.json.JSONObject
import java.nio.charset.Charset

object NewsProvider {
    fun provideNewsFromFile(assetManager: AssetManager, fileName: String = DEFAULT_FILE_NAME): List<News> {
        val inputStream = assetManager.open(fileName)
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
            newsItems.add(
                News(
                    title = newsObject.getStringOrNull(JSON_TITLE),
                    imageUri = newsObject.getStringOrNull(JSON_IMAGE_URL),
                    resourceName = newsObject.getStringOrNull(JSON_RESOURCE_NAME),
                    resourceUrl = newsObject.getStringOrNull(JSON_RESOURCE_URL),
                    newsLink = newsObject.getStringOrNull(JSON_NEWS_LINK)
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