package com.example.android.marvelcomicreader


import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import org.hamcrest.Matchers.allOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class MainActivityUiTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun mainActivityUiTest() {
        val textView = onView(
            allOf(
                withId(R.id.tvComicTitle),
                isDisplayed()
            )
        )
        textView.check(matches(isDisplayed()))
        textView.check(matches(withText("X-Men: Days of Future Past (Trade Paperback)")))

        val textView2 = onView(
            allOf(
                withId(R.id.tvDescription),
                isDisplayed()
            )
        )
        textView2.check(matches(isDisplayed()))

        val imageView = onView(
            allOf(
                withId(R.id.ivComicCover), withContentDescription("Cover Image"),
                isDisplayed()
            )
        )
        imageView.check(matches(isDisplayed()))

    }
}
