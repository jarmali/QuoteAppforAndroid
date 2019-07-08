package com.jarmali.quoteappforandroid
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jarmali.quoteappforandroid.di.DaggerAppComponent
import com.jarmali.quoteappforandroid.model.QuoteResponse
import com.jarmali.quoteappforandroid.model.QuotesService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import retrofit2.HttpException
import javax.inject.Inject

class QuotesViewModel : ViewModel() {
  @Inject
  lateinit var quotesService: QuotesService

  init {
    DaggerAppComponent.create().inject(this)
  }

  val compositeDisposable = CompositeDisposable()
  val quote = MutableLiveData<String>()

  fun getQuotes() : LiveData<String> {
    return quote
  }

  fun getInspiringQuote() {
    getQuote("inspire")
  }

  fun getManagementQuote() {
    getQuote("management")
  }

  fun getLoveQuote() {
    getQuote("love")
  }

  fun getFunnyQuote() {
    getQuote("funny")
  }

  fun getQuote(quoteType: String) {
    val disposable = quotesService.getQuote(quoteType)
      .subscribeOn(Schedulers.newThread())
      .observeOn(AndroidSchedulers.mainThread())
      .subscribeWith(object: DisposableSingleObserver<QuoteResponse>() {
        override fun onSuccess(t: QuoteResponse) {
          quote.value = t.contents.quotes[0].quote
        }

        override fun onError(e: Throwable) {
          if (e is HttpException && e.code() == 429) {
            quote.value = "Quotes API is rate limited, try again later."
          } else {
            quote.value = e.message
          }
        }
      })

    compositeDisposable.add(disposable)
  }

  override fun onCleared() {
    compositeDisposable.dispose()
    super.onCleared()
  }
}