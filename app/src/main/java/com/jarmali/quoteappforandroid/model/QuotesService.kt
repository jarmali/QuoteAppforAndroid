package com.jarmali.quoteappforandroid.model

import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class QuotesService {
  private val BASE_URL = "https://quotes.rest/"
  private val api : QuotesApi

  init {
    api = Retrofit.Builder()
      .baseUrl(BASE_URL)
      .addConverterFactory(GsonConverterFactory.create())
      .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
      .build()
      .create(QuotesApi::class.java)
  }

  fun getQuote(quoteType: String): Single<QuoteResponse> {
    return api.getQuote(quoteType)
  }
}