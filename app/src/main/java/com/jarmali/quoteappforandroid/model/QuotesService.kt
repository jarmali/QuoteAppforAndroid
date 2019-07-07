package com.jarmali.quoteappforandroid.model

import com.jarmali.quoteappforandroid.di.DaggerAppComponent
import io.reactivex.Single
import javax.inject.Inject

class QuotesService {
  @Inject
  lateinit var api : QuotesApi

  init {
    DaggerAppComponent.create().inject(this)
  }

  fun getQuote(quoteType: String): Single<QuoteResponse> {
    return api.getQuote(quoteType)
  }
}