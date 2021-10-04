package com.alexxer.simplepostapplication.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.alexxer.simplepostapplication.data.db.DatabaseParams

@Entity(tableName = "users")
data class UserEntity(
    @PrimaryKey
    @ColumnInfo(name = DatabaseParams.ID)
    val id: Int,
    @ColumnInfo(name = DatabaseParams.NAME)
    val name: String
)