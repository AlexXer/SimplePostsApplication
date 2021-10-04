package com.alexxer.simplepostapplication.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.alexxer.simplepostapplication.data.db.dao.PostDao
import com.alexxer.simplepostapplication.data.db.dao.UserDao
import com.alexxer.simplepostapplication.data.db.entity.PostEntity
import com.alexxer.simplepostapplication.data.db.entity.UserEntity

@Database(entities = [PostEntity::class, UserEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getPostDao(): PostDao

    abstract fun getUserDao(): UserDao
}