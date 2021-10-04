package com.alexxer.simplepostapplication.data.network.model

import com.alexxer.simplepostapplication.data.db.DatabaseParams
import com.alexxer.simplepostapplication.data.db.entity.UserEntity
import com.google.gson.annotations.SerializedName

data class UserDTO(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("username")
    val username: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("address")
    val address: Address,
    @SerializedName("phone")
    val phone: String,
    @SerializedName("website")
    val website: String,
    @SerializedName("company")
    val company: Company
)

fun UserDTO.toEntity() = UserEntity(id, name)