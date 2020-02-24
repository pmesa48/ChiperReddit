package com.pmesa.chiperreddit.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.pmesa.chiperreddit.repo.source.api.RetrofitRedditApi
import com.pmesa.chiperreddit.repo.source.cache.AppDatabase


class ViewModelFactory private constructor(
    private val application: Application,
    private val db: AppDatabase
) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        with(modelClass) {
            when {
                isAssignableFrom(SubRedditDetailViewModel::class.java) ->
                    SubRedditDetailViewModel(db, RetrofitRedditApi())
                isAssignableFrom(SubRedditListViewModel::class.java) ->
                    SubRedditListViewModel(application, db, RetrofitRedditApi())
                else ->
                    error("Invalid View Model class")
            }
        } as T

    companion object {
        @Volatile
        private var INSTANCE: ViewModelFactory? = null

        fun getInstance(application: Application): ViewModelFactory {
            return INSTANCE ?: synchronized(ViewModelFactory::class.java) {
                ViewModelFactory(
                    application,
                    AppDatabase.getDatabase(application)!!
                ).also { INSTANCE = it }
            }
        }
    }
}