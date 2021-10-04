package com.alexxer.simplepostapplication.data.db.entity

import com.alexxer.simplepostapplication.domain.model.UserPost

data class UserPostEntity(
    val name: String,
    val title: String
)

fun UserPostEntity.toDomain() = UserPost(name, title)