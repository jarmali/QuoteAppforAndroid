package com.jarmali.quoteappforandroid.model
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface QuotesApiService {
    @GET("qod.json")
    fun getQuote(@Query("category") quoteType: String): Single<QuoteResponse>

    companion object {

        fun create(): QuotesApiService {

            val BASE_URL = "https://quotes.rest/"

            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()

            return retrofit.create(QuotesApiService::class.java)

        }

    }

}