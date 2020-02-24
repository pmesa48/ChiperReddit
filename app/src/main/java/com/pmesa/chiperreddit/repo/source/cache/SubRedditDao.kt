package com.pmesa.chiperreddit.repo.source.cache

import androidx.room.*


@Dao
interface SubRedditDao {

    @Query("SELECT * FROM roomsubreddit")
    fun getAll() : List<RoomSubReddit>

    @Query("SELECT * FROM roomsubreddit WHERE url LIKE :url")
    fun getByUrl(url: String) : RoomSubReddit

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSubRedditList(list: List<RoomSubReddit>)
}