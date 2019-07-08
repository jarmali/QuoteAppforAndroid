package com.jarmali.quoteappforandroid.model

data class QuoteResponse(
  val contents: Contents,
  val success: Success
)

data class Success(
  val total: Int
)

data class Contents(
  val copyright: String,
  val quotes: List<Quote>
)

data class Quote(
//  val author: String,
//  val category: String,
//  val date: String,
//  val id: Any,
//  val length: String,
  val quote: String
//  val tags: List<String>,
//  val title: String
)