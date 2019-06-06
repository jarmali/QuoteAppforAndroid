package com.jarmali.quoteappforandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewModel = ViewModelProviders.of(this).get(QuotesViewModel::class.java)
        viewModel.getQuotes().observe(this, quoteObserver)

        managementQuoteButton.setOnClickListener {
            textView.text = "Loading ..."
            viewModel.getManagementQuote()
        }

        inspiringQuoteButton.setOnClickListener {
            textView.text = "Loading ..."
            viewModel.getInspiringQuote()
        }

        loveQuoteButton.setOnClickListener {
            textView.text = "Loading ..."
            viewModel.getLoveQuote()
        }

        funnyQuoteButton.setOnClickListener {
            textView.text = "Loading ..."
            viewModel.getFunnyQuote()
        }

    }

    val quoteObserver = Observer<String> {
        textView.text = it.toString()

    }
}
