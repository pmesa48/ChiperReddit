package com.pmesa.chiperreddit.repo.source.api

import android.util.Log
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class RetrofitRedditApi : RedditApi {

    private val mService: RedditApiServices = retrofit.create<RedditApiServices>(
            RedditApiServices::class.java)

    override fun getContent(callback: (List<SubReddit>?, error : Boolean) -> Unit) {
        mService.getContent()?.enqueue(object : Callback<GetContentResponse?>{
            override fun onResponse(
                call: Call<GetContentResponse?>,
                response: Response<GetContentResponse?>
            ) {
                response.body()?.content?.let { data ->
                    data.subReddits?.let {
                        callback(it, false)
                    } ?: run { callback(null, true)}
                } ?: run { callback(null, true) }
            }

            override fun onFailure(call: Call<GetContentResponse?>, t: Throwable) {
                Log.e(TAG, t.message, t)
                callback(null, true)
            }

        })
    }

    companion object{
        val TAG = RetrofitRedditApi::class.java.simpleName
        val retrofit: Retrofit =
            Retrofit.Builder().baseUrl("https://reddit.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(getHttpClient())
                .build()
        private fun getHttpClient(): OkHttpClient {
            val logging = HttpLoggingInterceptor()
            logging.setLevel(HttpLoggingInterceptor.Level.BODY)
            val httpClient = OkHttpClient.Builder()
            httpClient.addInterceptor(logging)
            return httpClient.build()
        }
    }
}