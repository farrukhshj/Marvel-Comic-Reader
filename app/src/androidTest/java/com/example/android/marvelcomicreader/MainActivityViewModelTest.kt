package com.example.android.marvelcomicreader

import android.support.annotation.VisibleForTesting
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.android.marvelcomicreader.model.ComicData
import com.google.gson.Gson
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.doReturn
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit
import java.util.concurrent.TimeoutException

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
class MainActivityViewModelTest {


    private val successJson: String = "{\n" +
            "  \"code\": 200,\n" +
            "  \"status\": \"Ok\",\n" +
            "  \"copyright\": \"© 2022 MARVEL\",\n" +
            "  \"attributionText\": \"Data provided by Marvel. © 2022 MARVEL\",\n" +
            "  \"attributionHTML\": \"<a href=\\\"http://marvel.com\\\">Data provided by Marvel. © 2022 MARVEL</a>\",\n" +
            "  \"etag\": \"8465730a557adc2fed3b35da98554a4f13a8bf40\",\n" +
            "  \"data\": {\n" +
            "    \"offset\": 0,\n" +
            "    \"limit\": 20,\n" +
            "    \"total\": 1,\n" +
            "    \"count\": 1,\n" +
            "    \"results\": [\n" +
            "      {\n" +
            "        \"id\": 1332,\n" +
            "        \"digitalId\": 0,\n" +
            "        \"title\": \"X-Men: Days of Future Past (Trade Paperback)\",\n" +
            "        \"issueNumber\": 0,\n" +
            "        \"variantDescription\": \"\",\n" +
            "        \"description\": \"\\\"Re-live the legendary first journey into the dystopian future of 2013 - where Sentinels stalk the Earth, and the X-Men are humanity's only hope...until they die! Also featuring the first appearance of Alpha Flight, the return of the Wendigo, the history of the X-Men from Cyclops himself...and a demon for Christmas!? \\\"\",\n" +
            "        \"modified\": \"2017-02-28T14:52:22-0500\",\n" +
            "        \"isbn\": \"0-7851-1560-9\",\n" +
            "        \"upc\": \"5960611560-00111\",\n" +
            "        \"diamondCode\": \"\",\n" +
            "        \"ean\": \"\",\n" +
            "        \"issn\": \"\",\n" +
            "        \"format\": \"Trade Paperback\",\n" +
            "        \"pageCount\": 144,\n" +
            "        \"textObjects\": [\n" +
            "          {\n" +
            "            \"type\": \"issue_solicit_text\",\n" +
            "            \"language\": \"en-us\",\n" +
            "            \"text\": \"\\\"Re-live the legendary first journey into the dystopian future of 2013 - where Sentinels stalk the Earth, and the X-Men are humanity's only hope...until they die! Also featuring the first appearance of Alpha Flight, the return of the Wendigo, the history of the X-Men from Cyclops himself...and a demon for Christmas!? \\\"\"\n" +
            "          }\n" +
            "        ],\n" +
            "        \"resourceURI\": \"http://gateway.marvel.com/v1/public/comics/1332\",\n" +
            "        \"urls\": [\n" +
            "          {\n" +
            "            \"type\": \"detail\",\n" +
            "            \"url\": \"http://marvel.com/comics/collection/1332/x-men_days_of_future_past_trade_paperback?utm_campaign=apiRef&utm_source=a7736ea2d8ad8e409ebe17bad1892ee0\"\n" +
            "          }\n" +
            "        ],\n" +
            "        \"series\": {\n" +
            "          \"resourceURI\": \"http://gateway.marvel.com/v1/public/series/1327\",\n" +
            "          \"name\": \"X-Men: Days of Future Past (2004)\"\n" +
            "        },\n" +
            "        \"variants\": [],\n" +
            "        \"collections\": [],\n" +
            "        \"collectedIssues\": [\n" +
            "          {\n" +
            "            \"resourceURI\": \"http://gateway.marvel.com/v1/public/comics/13683\",\n" +
            "            \"name\": \"Uncanny X-Men (1963) #142\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"resourceURI\": \"http://gateway.marvel.com/v1/public/comics/12460\",\n" +
            "            \"name\": \"Uncanny X-Men (1963) #141\"\n" +
            "          }\n" +
            "        ],\n" +
            "        \"dates\": [\n" +
            "          {\n" +
            "            \"type\": \"onsaleDate\",\n" +
            "            \"date\": \"2029-12-31T00:00:00-0500\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"type\": \"focDate\",\n" +
            "            \"date\": \"-0001-11-30T00:00:00-0500\"\n" +
            "          }\n" +
            "        ],\n" +
            "        \"prices\": [\n" +
            "          {\n" +
            "            \"type\": \"printPrice\",\n" +
            "            \"price\": 9.99\n" +
            "          }\n" +
            "        ],\n" +
            "        \"thumbnail\": {\n" +
            "          \"path\": \"http://i.annihil.us/u/prod/marvel/i/mg/9/d0/58b5cfb6d5239\",\n" +
            "          \"extension\": \"jpg\"\n" +
            "        },\n" +
            "        \"images\": [\n" +
            "          {\n" +
            "            \"path\": \"http://i.annihil.us/u/prod/marvel/i/mg/9/d0/58b5cfb6d5239\",\n" +
            "            \"extension\": \"jpg\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"path\": \"http://i.annihil.us/u/prod/marvel/i/mg/9/b0/4bc66463ef7f0\",\n" +
            "            \"extension\": \"jpg\"\n" +
            "          }\n" +
            "        ],\n" +
            "        \"creators\": {\n" +
            "          \"available\": 0,\n" +
            "          \"collectionURI\": \"http://gateway.marvel.com/v1/public/comics/1332/creators\",\n" +
            "          \"items\": [],\n" +
            "          \"returned\": 0\n" +
            "        },\n" +
            "        \"characters\": {\n" +
            "          \"available\": 10,\n" +
            "          \"collectionURI\": \"http://gateway.marvel.com/v1/public/comics/1332/characters\",\n" +
            "          \"items\": [\n" +
            "            {\n" +
            "              \"resourceURI\": \"http://gateway.marvel.com/v1/public/characters/1009159\",\n" +
            "              \"name\": \"Archangel\"\n" +
            "            },\n" +
            "            {\n" +
            "              \"resourceURI\": \"http://gateway.marvel.com/v1/public/characters/1009164\",\n" +
            "              \"name\": \"Avalanche\"\n" +
            "            },\n" +
            "            {\n" +
            "              \"resourceURI\": \"http://gateway.marvel.com/v1/public/characters/1009199\",\n" +
            "              \"name\": \"Blob\"\n" +
            "            },\n" +
            "            {\n" +
            "              \"resourceURI\": \"http://gateway.marvel.com/v1/public/characters/1009243\",\n" +
            "              \"name\": \"Colossus\"\n" +
            "            },\n" +
            "            {\n" +
            "              \"resourceURI\": \"http://gateway.marvel.com/v1/public/characters/1009271\",\n" +
            "              \"name\": \"Destiny\"\n" +
            "            },\n" +
            "            {\n" +
            "              \"resourceURI\": \"http://gateway.marvel.com/v1/public/characters/1009472\",\n" +
            "              \"name\": \"Nightcrawler\"\n" +
            "            },\n" +
            "            {\n" +
            "              \"resourceURI\": \"http://gateway.marvel.com/v1/public/characters/1009522\",\n" +
            "              \"name\": \"Pyro\"\n" +
            "            },\n" +
            "            {\n" +
            "              \"resourceURI\": \"http://gateway.marvel.com/v1/public/characters/1009629\",\n" +
            "              \"name\": \"Storm\"\n" +
            "            },\n" +
            "            {\n" +
            "              \"resourceURI\": \"http://gateway.marvel.com/v1/public/characters/1009718\",\n" +
            "              \"name\": \"Wolverine\"\n" +
            "            },\n" +
            "            {\n" +
            "              \"resourceURI\": \"http://gateway.marvel.com/v1/public/characters/1009726\",\n" +
            "              \"name\": \"X-Men\"\n" +
            "            }\n" +
            "          ],\n" +
            "          \"returned\": 10\n" +
            "        },\n" +
            "        \"stories\": {\n" +
            "          \"available\": 3,\n" +
            "          \"collectionURI\": \"http://gateway.marvel.com/v1/public/comics/1332/stories\",\n" +
            "          \"items\": [\n" +
            "            {\n" +
            "              \"resourceURI\": \"http://gateway.marvel.com/v1/public/stories/15472\",\n" +
            "              \"name\": \"Days of Future Past\",\n" +
            "              \"type\": \"interiorStory\"\n" +
            "            },\n" +
            "            {\n" +
            "              \"resourceURI\": \"http://gateway.marvel.com/v1/public/stories/27788\",\n" +
            "              \"name\": \"Mind Out of Time!\",\n" +
            "              \"type\": \"interiorStory\"\n" +
            "            },\n" +
            "            {\n" +
            "              \"resourceURI\": \"http://gateway.marvel.com/v1/public/stories/65738\",\n" +
            "              \"name\": \"X-MEN: DAYS OF FUTURE PAST TPB 0 cover\",\n" +
            "              \"type\": \"cover\"\n" +
            "            }\n" +
            "          ],\n" +
            "          \"returned\": 3\n" +
            "        },\n" +
            "        \"events\": {\n" +
            "          \"available\": 0,\n" +
            "          \"collectionURI\": \"http://gateway.marvel.com/v1/public/comics/1332/events\",\n" +
            "          \"items\": [],\n" +
            "          \"returned\": 0\n" +
            "        }\n" +
            "      }\n" +
            "    ]\n" +
            "  }\n" +
            "}"

    @Mock
    private lateinit var comicService : ComicService

    @Mock
    private lateinit var comicRepository : ComicRepository

    private lateinit var viewModel: MainActivityViewModel



    @get:Rule
    val instantTaskExecutionRule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        comicRepository = ComicRepository(comicService)
        viewModel = MainActivityViewModel(comicRepository)
    }

    @Test
    fun givenServerResponse200_whenFetch_shouldReturnSuccess() {
        runBlocking {
            doReturn(Gson().fromJson(successJson,ComicData::class.java))
                .`when`(comicRepository)
                .getComic(1223,1212121,"dfdffs","sdsfsfgsdjfsc")
            val viewModel = MainActivityViewModel(comicRepository)
            verify(comicRepository).getComic(1223,1212121,"dfdffs","sdsfsfgsdjfsc")

        }
    }


}


@VisibleForTesting(otherwise = VisibleForTesting.NONE)
fun <T> LiveData<T>.getOrAwaitValue(
    time: Long = 2,
    timeUnit: TimeUnit = TimeUnit.SECONDS,
    afterObserve: () -> Unit = {}
): T {
    var data: T? = null
    val latch = CountDownLatch(1)
    val observer = object : Observer<T> {
        override fun onChanged(o: T?) {
            data = o
            latch.countDown()
            this@getOrAwaitValue.removeObserver(this)
        }
    }
    this.observeForever(observer)
    try {
        afterObserve.invoke()
        if (!latch.await(time, timeUnit)) {
            throw TimeoutException("LiveData value was never set")
        }
    } finally {
        this.removeObserver(observer)
    }
    @Suppress("UNCHECKED_CAST")
    return data as T
}

