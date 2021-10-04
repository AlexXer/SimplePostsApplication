package com.alexxer.simplepostapplication.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.alexxer.simplepostapplication.data.db.DatabaseParams

@Entity(
    tableName = DatabaseParams.POST_TABLE_NAME,
    foreignKeys = [
        ForeignKey(
            entity = UserEntity::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("user_id"),
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class PostEntity (
    @PrimaryKey
    @ColumnInfo(name = DatabaseParams.POST_ID)
    val id: Int,
    @ColumnInfo(name = DatabaseParams.USER_ID)
    val userId: Int,
    @ColumnInfo(name = DatabaseParams.TITLE)
    val title: String,
    @ColumnInfo(name = DatabaseParams.BODY)
    val body: String
)