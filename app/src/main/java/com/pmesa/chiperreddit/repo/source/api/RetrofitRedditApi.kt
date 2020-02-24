package com.pmesa.chiperreddit.repo.source.api

import android.util.Log
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class RetrofitRedditApi(retrofit: Retrofit) : RedditApi {

    private val mService: RedditApiServices = retrofit.create<RedditApiServices>(
            RedditApiServices::class.java)

    override fun getContent(callback: (List<SubRedditDto>?, error : Boolean) -> Unit) {
        mService.getContent()?.enqueue(object : Callback<GetContentResponseDto?>{
            override fun onResponse(
                call: Call<GetContentResponseDto?>,
                response: Response<GetContentResponseDto?>
            ) {
                response.body()?.content?.let { data ->
                    data.subRedditDtos?.let {
                        callback(it, false)
                    } ?: run { callback(null, true)}
                } ?: run { callback(null, true) }
            }

            override fun onFailure(call: Call<GetContentResponseDto?>, t: Throwable) {
                Log.e(TAG, t.message, t)
                callback(null, true)
            }

        })
    }

    companion object{
        val TAG = RetrofitRedditApi::class.java.simpleName

        var instance: RetrofitRedditApi? = null

        private fun getHttpClient(): OkHttpClient {
            val logging = HttpLoggingInterceptor()
            logging.setLevel(HttpLoggingInterceptor.Level.BODY)
            val httpClient = OkHttpClient.Builder()
            httpClient.addInterceptor(logging)
            return httpClient.build()
        }

        fun getIntance(): RedditApi {
            if(instance == null){
                instance = RetrofitRedditApi(Retrofit.Builder().baseUrl("https://reddit.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(getHttpClient())
                    .build())
            }
            return instance as RetrofitRedditApi
        }
    }
}