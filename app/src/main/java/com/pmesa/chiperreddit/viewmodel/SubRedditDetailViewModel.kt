package com.pmesa.chiperreddit.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pmesa.chiperreddit.repo.SubRedditRepository
import com.pmesa.chiperreddit.repo.source.api.RedditApi
import com.pmesa.chiperreddit.repo.source.cache.AppDatabase
import com.pmesa.chiperreddit.repo.source.cache.RoomSubReddit

class SubRedditDetailViewModel(db: AppDatabase, api: RedditApi) : ViewModel() {

    private var subRedditRepository : SubRedditRepository = SubRedditRepository.getInstance(db, api)


    private var subreddit: MutableLiveData<RoomSubReddit>? = null

    fun getSubReddit(url: String): MutableLiveData<RoomSubReddit>? {
        if(subreddit == null){
            subreddit = MutableLiveData()
            getSubRedditByUrl(url)
        }
        return subreddit
    }

    private fun getSubRedditByUrl(url: String){
        subRedditRepository.get(url) { model ->
            subreddit?.value = model
        }
    }

}