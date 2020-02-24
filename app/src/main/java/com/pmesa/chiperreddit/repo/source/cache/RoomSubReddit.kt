package com.pmesa.chiperreddit.repo.source.cache

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.pmesa.chiperreddit.repo.source.api.SubReddit

@Entity
data class RoomSubReddit(
    @PrimaryKey val id: String,
    @ColumnInfo(name = "name") val name: String?,
    @ColumnInfo(name = "display_name") val displayName: String?,
    @ColumnInfo(name = "description") val description: String?,
    @ColumnInfo(name = "url") val url: String?,
    @ColumnInfo(name = "icon") val icon: String?
) {
    fun toModel(): SubReddit {
        val model =  SubReddit()
        model.childData?.name = name
        model.childData?.description = description
        model.childData?.displayName = displayName
        model.childData?.url = url
        return model
    }
}