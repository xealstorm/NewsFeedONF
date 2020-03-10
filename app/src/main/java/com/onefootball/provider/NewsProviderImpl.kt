package com.onefootball.provider

import android.content.res.AssetManager
import android.util.Log
import com.onefootball.model.News
import com.onefootball.util.forEach
import com.onefootball.util.getStringOrNull
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import java.nio.charset.Charset

class NewsProviderImpl(
    private val assetManager: AssetManager,
    private val fileName: String = DEFAULT_FILE_NAME
) : NewsProvider {
    override fun provideNews() = try {
        val inputStream = assetManager.open(fileName)
        val size = inputStream.available()
        val buffer = ByteArray(size)
        inputStream.read(buffer)
        inputStream.close()
        val jsonString = buffer.toString(Charset.defaultCharset())
        parseJsonString(jsonString)
    } catch (e: IOException) {
        Log.e(NewsProvider::class.java.toString(), e.message ?: "Unable to read the file")
        listOf<News>()
    }


    private fun parseJsonString(jsonString: String) =
        try {
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
            newsItems
        } catch (e: JSONException) {
            Log.e(NewsProvider::class.java.toString(), e.message ?: "Unable to parse the file")
            listOf<News>()
        }

    companion object {
        private const val DEFAULT_FILE_NAME = "news.json"
        private const val JSON_NEWS = "news"
        private const val JSON_TITLE = "title"
        private const val JSON_IMAGE_URL = "image_url"
        private const val JSON_RESOURCE_NAME = "resource_name"
        private const val JSON_RESOURCE_URL = "resource_url"
        private const val JSON_NEWS_LINK = "news_link"
    }
}