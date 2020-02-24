package com.pmesa.chiperreddit.repo.source.api

interface RedditApi {

    fun getContent(callback: (List<SubReddit>?, Boolean) -> Unit)
}