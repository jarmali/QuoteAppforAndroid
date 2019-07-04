package com.jarmali.quoteappforandroid.model
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface QuotesApi {
  @GET("qod.json")
  fun getQuote(@Query("category") quoteType: String): Single<QuoteResponse>
}