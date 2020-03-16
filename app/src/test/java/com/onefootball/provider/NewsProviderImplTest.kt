package com.onefootball.provider

import android.content.res.AssetManager
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.doReturn
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import java.io.FileInputStream


@RunWith(MockitoJUnitRunner::class)
class NewsProviderImplTest {
    @Mock
    internal var assetManager: AssetManager = Mockito.mock(AssetManager::class.java)

    var provider: NewsProviderImpl? = null

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        provider = NewsProviderImpl(
            assetManager
        )
    }

    @Test
    fun `the provider can read big files`() {
        val spyProvider = Mockito.spy(provider)
        doReturn(FileInputStream("../app/src/androidTest/assets/$HUGE_FILE_NAME"))
            .`when`(spyProvider)!!
            .provideInputStream(HUGE_FILE_NAME)
        val result = spyProvider!!.getJsonStringFromFile(HUGE_FILE_NAME)
        Assert.assertNotNull(result)
        Assert.assertNotEquals("", result)
    }

    @Test
    fun `the provider can read empty files`() {
        val spyProvider = Mockito.spy(provider)
        doReturn(FileInputStream("../app/src/androidTest/assets/$EMPTY_FILE_NAME"))
            .`when`(spyProvider)!!
            .provideInputStream(EMPTY_FILE_NAME)
        val result = spyProvider!!.getJsonStringFromFile(EMPTY_FILE_NAME)
        Assert.assertNotNull(result)
        Assert.assertEquals("", result)
    }

    @Test
    fun `the provider parses big files correctly`() {
        val spyProvider = Mockito.spy(provider)
        doReturn(FileInputStream("../app/src/androidTest/assets/$HUGE_FILE_NAME"))
            .`when`(spyProvider)!!
            .provideInputStream(HUGE_FILE_NAME)
        val result = spyProvider!!.parseJsonString(spyProvider.getJsonStringFromFile(HUGE_FILE_NAME))
        Assert.assertNotNull(result)
        Assert.assertEquals(HUGE_FILE_ELEMENTS_COUNT, result.size)
    }

    @Test
    fun `the provider parses elements with missing fields`() {
        val spyProvider = Mockito.spy(provider)
        doReturn(FileInputStream("../app/src/androidTest/assets/$MISSING_FIELDS_FILE_NAME"))
            .`when`(spyProvider)!!
            .provideInputStream(MISSING_FIELDS_FILE_NAME)
        val result = spyProvider!!.parseJsonString(spyProvider.getJsonStringFromFile(MISSING_FIELDS_FILE_NAME))
        Assert.assertNotNull(result)
        Assert.assertEquals(MISSING_FIELDS_FILE_ELEMENTS_COUNT, result.size)
    }

    @Test
    fun `the provider parses elements with empty fields`() {
        val spyProvider = Mockito.spy(provider)
        doReturn(FileInputStream("../app/src/androidTest/assets/$EMPTY_FIELDS_FILE_NAME"))
            .`when`(spyProvider)!!
            .provideInputStream(EMPTY_FIELDS_FILE_NAME)
        val result = spyProvider!!.parseJsonString(spyProvider.getJsonStringFromFile(EMPTY_FIELDS_FILE_NAME))
        Assert.assertNotNull(result)
        Assert.assertEquals(EMPTY_FIELDS_FILE_ELEMENTS_COUNT, result.size)
    }

    @Test
    fun `the provider parses empty file`() {
        val result = provider!!.parseJsonString("")
        Assert.assertNotNull(result)
        Assert.assertEquals(EMPTY_FILE_ELEMENTS_COUNT, result.size)
    }

    companion object {
        private const val HUGE_FILE_NAME = "news_huge_file.json"
        private const val HUGE_FILE_ELEMENTS_COUNT = 6160
        private const val EMPTY_FIELDS_FILE_NAME = "news_empty_fields.json"
        private const val EMPTY_FIELDS_FILE_ELEMENTS_COUNT = 10
        private const val MISSING_FIELDS_FILE_NAME = "news_missing_fields.json"
        private const val MISSING_FIELDS_FILE_ELEMENTS_COUNT = 10
        private const val EMPTY_FILE_NAME = "news_empty.json"
        private const val EMPTY_FILE_ELEMENTS_COUNT = 0
    }
}
