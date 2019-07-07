package com.jarmali.quoteappforandroid.di

import com.jarmali.quoteappforandroid.QuotesViewModel
import com.jarmali.quoteappforandroid.model.QuotesService
import dagger.Component

@Component(modules = [ApiModule::class])
interface AppComponent {
  fun inject(service: QuotesService)

  fun inject(viewModel: QuotesViewModel)
}