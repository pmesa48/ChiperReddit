package com.pmesa.chiperreddit.repo.source.api

import retrofit2.Call
import retrofit2.http.GET


interface RedditApiServices {

    @GET("reddits.json")
    fun getContent(): Call<GetContentResponseDto?>?
}