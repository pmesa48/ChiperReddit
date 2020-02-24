package com.pmesa.chiperreddit.repo.source.api

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.pmesa.chiperreddit.repo.source.cache.RoomSubReddit

class GetContentResponseDto {
    @SerializedName("data")
    @Expose
    var content: ContentDto? = null
}

class ContentDto {
    @SerializedName("children")
    @Expose
    var subRedditDtos: List<SubRedditDto>? = null
}

class SubRedditDto {
    @SerializedName("data")
    @Expose
    var childData: SubRedditDtoData? = null

    fun toRoom(): RoomSubReddit {
        return RoomSubReddit(childData?.url ?: "", childData?.name, childData?.displayName, childData?.description, childData?.url, childData?.iconImg)
    }
}

class SubRedditDtoData {
    @SerializedName("display_name")
    @Expose
    var displayName: String? = null
    @SerializedName("icon_img")
    @Expose
    var iconImg: String? = null
    @SerializedName("name")
    @Expose
    var name: String? = null
    @SerializedName("over18")
    @Expose
    private var over18 = false
    @SerializedName("description")
    @Expose
    var description: String? = null
    @SerializedName("lang")
    @Expose
    private var lang: String? = null
    @SerializedName("url")
    @Expose
    var url: String? = null
}
