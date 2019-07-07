package com.jarmali.quoteappforandroid.di

import com.jarmali.quoteappforandroid.model.QuotesApi
import com.jarmali.quoteappforandroid.model.QuotesService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
class ApiModule {
  private val BASE_URL = "https://quotes.rest/"

  @Provides
  fun provideQuotesApi(): QuotesApi {
    return Retrofit.Builder()
      .baseUrl(BASE_URL)
      .addConverterFactory(GsonConverterFactory.create())
      .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
      .build()
      .create(QuotesApi::class.java)
  }

  @Provides
  fun provideQuotesService(): QuotesService {
    return QuotesService()
  }
}