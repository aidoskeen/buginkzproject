package com.daryn.buginkzproject

import com.daryn.buginkzproject.models.NewsList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers

interface NewsApi {
    @Headers(
        "Accept: application/json",
        "Content-type:application/json"
    )
    @GET("api/news/")
    fun getNews() : Call<NewsList>
}