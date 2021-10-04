package com.alexxer.simplepostapplication.data.network.model

import com.alexxer.simplepostapplication.data.db.DatabaseParams
import com.alexxer.simplepostapplication.data.db.entity.PostEntity
import com.google.gson.annotations.SerializedName

data class PostDTO(
    @SerializedName("id")
    val id: Int,
    @SerializedName("userId")
    val userId: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("body")
    val body: String
)

fun PostDTO.toEntity() = PostEntity(id, userId, title, body)