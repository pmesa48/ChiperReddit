package com.pmesa.chiperreddit.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.pmesa.chiperreddit.repo.SubRedditRepository
import com.pmesa.chiperreddit.repo.source.api.RedditApi
import com.pmesa.chiperreddit.repo.source.cache.AppDatabase
import com.pmesa.chiperreddit.repo.source.cache.RoomSubReddit
import kotlinx.coroutines.launch

class SubRedditListViewModel(application: Application, db: AppDatabase, api: RedditApi) : AndroidViewModel(application) {

    private var subRedditRepository : SubRedditRepository = SubRedditRepository.getInstance(db, api)

    private var subreddits: MutableLiveData<List<RoomSubReddit>>? = null

    fun getSubReddits(): MutableLiveData<List<RoomSubReddit>> {
        if(subreddits == null){
            subreddits = MutableLiveData()
            loadSubReddits()
        }
        return subreddits!!
    }

    fun loadSubReddits() {
        viewModelScope.launch {
            subRedditRepository.get { list ->
                subreddits?.value = list
            }
        }
    }

}