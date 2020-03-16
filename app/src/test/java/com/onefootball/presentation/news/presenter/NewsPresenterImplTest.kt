package com.onefootball.presentation.news.presenter

import com.onefootball.model.News
import com.onefootball.presentation.news.model.NewsItem
import com.onefootball.presentation.news.ui.NewsView
import com.onefootball.provider.NewsProvider
import com.onefootball.provider.NewsProviderImpl
import com.onefootball.util.scedulers.TestSchedulerProvider
import io.reactivex.Observable
import io.reactivex.schedulers.TestScheduler
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class RatesPresenterImplTest {
    @Mock
    internal var newsProvider: NewsProvider = Mockito.mock(NewsProviderImpl::class.java)

    @Mock
    internal var view: NewsView? = null

    private lateinit var testScheduler: TestScheduler

    var presenter: NewsPresenterImpl? = null

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        testScheduler = TestScheduler()
        val testSchedulerProvider = TestSchedulerProvider(testScheduler)
        presenter = NewsPresenterImpl(
            newsProvider,
            testSchedulerProvider
        )
        presenter!!.setView(view!!)
    }

    @Test
    fun `provider triggers provideNews method when the presenter triggers provideNews method`() {
        Mockito.doReturn(Observable.fromCallable { listOf<News>() }).`when`(newsProvider)
            .provideNews()
        presenter!!.provideNews()
        testScheduler.triggerActions()
        Mockito.verify(newsProvider)!!.provideNews()
    }

    @Test
    fun `updateList is triggered after the parsing call returns a result`() {
        Mockito.doReturn(Observable.fromCallable { listOf<News>() }).`when`(newsProvider)
            .provideNews()
        val spyPresenter = Mockito.spy(presenter)
        spyPresenter!!.provideNews()
        testScheduler.triggerActions()
        Mockito.verify(spyPresenter)?.updateList(listOf())
    }

    @Test
    fun `presenter filters items with null fields`() {
        Mockito.doReturn(Observable.fromCallable { NEWS_MISSING_FIELDS_MOCK }).`when`(newsProvider)
            .provideNews()
        val spyPresenter = Mockito.spy(presenter)
        spyPresenter!!.provideNews()
        testScheduler.triggerActions()
        Mockito.verify(spyPresenter)?.updateList(NEWS_ITEMS_VALID_MOCK)
    }

    @Test
    fun `update items in UI if they are returned`() {
        val spyPresenter = Mockito.spy(presenter)
        spyPresenter!!.updateList(NEWS_ITEMS_MOCK)
        Mockito.verify(spyPresenter.getView())!!.updateNewsWithList(NEWS_ITEMS_MOCK)
    }

    companion object {
        val NEWS_ITEMS_MOCK: List<NewsItem> = listOf(
            NewsItem(
                "Sample title1",
                "https://sample1.jpg",
                "SampleResource1",
                "https://resource1.jpg",
                "https://newslink1.com"
            ),
            NewsItem(
                "Sample title2",
                "https://sample2.jpg",
                "SampleResource2",
                "https://resource2.jpg",
                "https://newslink2.com"
            ),
            NewsItem(
                "Sample title3",
                "https://sample3.jpg",
                "SampleResource3",
                "https://resource3.jpg",
                "https://newslink3.com"
            )
        )

        val NEWS_MISSING_FIELDS_MOCK: List<News> = listOf(
            News(
                null,
                "https://sample1.jpg",
                "SampleResource1",
                "https://resource1.jpg",
                "https://newslink1.com"
            ),
            News(
                "Sample title2",
                null,
                "SampleResource2",
                "https://resource2.jpg",
                "https://newslink2.com"
            ),
            News(
                "Sample title3",
                "https://sample3.jpg",
                null,
                "https://resource3.jpg",
                "https://newslink3.com"
            ), News(
                "Sample title4",
                "https://sample4.jpg",
                "SampleResource4",
                null,
                "https://newslink4.com"
            ),
            News(
                "Sample title5",
                "https://sample5.jpg",
                "SampleResource5",
                "https://resource5.jpg",
                null
            ),
            News(
                "Sample title6",
                "https://sample6.jpg",
                "SampleResource6",
                "https://resource6.jpg",
                "https://newslink6.com"
            )
        )

        val NEWS_ITEMS_VALID_MOCK: List<NewsItem> = listOf(
            NewsItem(
                "Sample title6",
                "https://sample6.jpg",
                "SampleResource6",
                "https://resource6.jpg",
                "https://newslink6.com"
            )
        )
    }
}