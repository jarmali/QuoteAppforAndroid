package com.jarmali.quoteappforandroid

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.jarmali.quoteappforandroid.model.*
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.disposables.Disposable
import io.reactivex.internal.schedulers.ExecutorScheduler
import io.reactivex.plugins.RxJavaPlugins
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*
import org.junit.Rule
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import java.util.concurrent.Callable
import java.util.concurrent.Executor
import java.util.concurrent.TimeUnit

class QuotesViewModelTest {
  @get:Rule
  var rule = InstantTaskExecutorRule()

  @Before
  fun prepareRxJavaSchedulersForTesting() {
    val immediate = object : Scheduler() {
      override fun scheduleDirect(run: Runnable?, delay: Long, unit: TimeUnit?): Disposable {
        return super.scheduleDirect(run, 0, unit)
      }

      override fun createWorker(): Worker {
        return ExecutorScheduler.ExecutorWorker(Executor { it.run() })
      }
    }

    RxJavaPlugins.setInitIoSchedulerHandler { scheduler: Callable<Scheduler>? -> immediate }
    RxJavaPlugins.setInitComputationSchedulerHandler { scheduler: Callable<Scheduler>? -> immediate }
    RxJavaPlugins.setInitNewThreadSchedulerHandler { scheduler: Callable<Scheduler>? -> immediate }
    RxJavaPlugins.setInitSingleSchedulerHandler { scheduler: Callable<Scheduler>? -> immediate }
    RxAndroidPlugins.setInitMainThreadSchedulerHandler { scheduler: Callable<Scheduler>? -> immediate }
  }

  @Mock
  lateinit var quotesService: QuotesService

  @InjectMocks
  var quotesViewModel = QuotesViewModel()

  private var testSingle: Single<QuoteResponse>? = null

  @Before
  fun setUp() {
    MockitoAnnotations.initMocks(this)
  }

  val managementQuote = "Management Quote"
  val loveQuote = "Love Quote"
  val inspiringQuote = "Inspiring Quote"
  val funnyQuote = "Funny Quote"

  @Test
  fun getManagementQuote_shouldUpdateLiveDataWithQuoteString() {
    val success = Success(1 )
    val managementQuoteResponse = QuoteResponse(Contents("Test Copyright", listOf(Quote(managementQuote))), success)

    testSingle = Single.just(managementQuoteResponse)

    `when`(quotesService.getQuote("management")).thenReturn(testSingle)

    quotesViewModel.getManagementQuote()

    assertEquals(managementQuote, quotesViewModel.quote.value)
  }

  @Test
  fun getInspiringQuote_shouldUpdateLiveDataWithQuoteString() {
    val success = Success(1 )
    val inspiringQuoteResponse = QuoteResponse(Contents("Test Copyright", listOf(Quote(inspiringQuote))), success)

    testSingle = Single.just(inspiringQuoteResponse)

    `when`(quotesService.getQuote("inspire")).thenReturn(testSingle)

    quotesViewModel.getInspiringQuote()

    assertEquals(inspiringQuote, quotesViewModel.quote.value)
  }

  @Test
  fun getFunnyQuote_shouldUpdateLiveDataWithQuoteString() {
    val success = Success(1 )
    val funnyQuoteResponse = QuoteResponse(Contents("Test Copyright", listOf(Quote(funnyQuote))), success)

    testSingle = Single.just(funnyQuoteResponse)

    `when`(quotesService.getQuote("funny")).thenReturn(testSingle)

    quotesViewModel.getFunnyQuote()

    assertEquals(funnyQuote, quotesViewModel.quote.value)
  }

  @Test
  fun getLoveQuote_shouldUpdateLiveDataWithQuoteString() {
    val success = Success(1 )
    val loveQuoteResponse = QuoteResponse(Contents("Test Copyright", listOf(Quote(loveQuote))), success)

    testSingle = Single.just(loveQuoteResponse)

    `when`(quotesService.getQuote("love")).thenReturn(testSingle)

    quotesViewModel.getLoveQuote()

    assertEquals(loveQuote, quotesViewModel.quote.value)
  }
}