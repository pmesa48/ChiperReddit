package com.pmesa.chiperreddit.repo

import com.pmesa.chiperreddit.repo.source.api.RedditApi
import com.pmesa.chiperreddit.repo.source.api.SubReddit
import com.pmesa.chiperreddit.repo.source.cache.AppDatabase
import com.pmesa.chiperreddit.repo.source.cache.RoomSubReddit
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class SubRedditRepository(
    private val db: AppDatabase,
    private val mApi: RedditApi) : CoroutineScope {


    fun get(callback: (List<RoomSubReddit>, Boolean) -> Unit) {
            mApi.getContent { list, error ->
                if (!error) {
                    fetchFromApi(list, callback)
                } else {
                    fetchFromCache(callback)
                }
            }
     }

    private fun fetchFromCache(callback: (List<RoomSubReddit>, Boolean) -> Unit) {
        launch(Dispatchers.IO) {
            val all = db.subredditDao().getAll()
            launch(Dispatchers.Main) {
                callback(all, false)
            }
        }
    }

    private fun fetchFromApi(
        list: List<SubReddit>?,
        callback: (List<RoomSubReddit>, Boolean) -> Unit
    ) {
        list?.let { all ->
            launch(Dispatchers.IO) {
                db.subredditDao().insertSubRedditList(all.map { it.toRoom() })
                launch(Dispatchers.Main) { callback(all.map { it.toRoom() }, true) }
            }
        }
    }

    fun get(url: String, callback: (RoomSubReddit, Boolean) -> Unit) {
        GlobalScope.launch(Dispatchers.IO) {
            val subReddit = db.subredditDao().getByUrl(url)
            GlobalScope.launch(Dispatchers.Main) {
                callback(subReddit, false)
            }
        }
    }

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main

    companion object {
        private var INSTANCE: SubRedditRepository? = null

        fun getInstance(
            db: AppDatabase,
            api: RedditApi
        ): SubRedditRepository = INSTANCE
            ?: synchronized(SubRedditRepository::class.java) {
                SubRedditRepository(db, api)
                    .also { INSTANCE = it }
            }
    }
}