package com.pmesa.chiperreddit.repo.source.cache

import com.pmesa.chiperreddit.repo.source.api.SubReddit

class RedditContentCache(private val database: AppDatabase) {

    fun update(it: List<SubReddit>) {
    }

    fun getAll(): List<RoomSubReddit> = emptyList()

    fun getByUrl(url: String, callback: (SubReddit, Boolean) -> Unit) {

        //callback(database.subredditDao().getByUrl(url).toModel(), true)
    }

}
