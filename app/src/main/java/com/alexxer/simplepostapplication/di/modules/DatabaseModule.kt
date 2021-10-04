package com.alexxer.simplepostapplication.di.modules

import android.content.Context
import androidx.room.Room
import com.alexxer.simplepostapplication.data.db.AppDatabase
import com.alexxer.simplepostapplication.data.db.dao.PostDao
import com.alexxer.simplepostapplication.data.db.dao.UserDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(context: Context): AppDatabase =
        Room
            .databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .build()

    @Provides
    fun providePostsDao(database: AppDatabase): PostDao = database.getPostDao()

    @Provides
    fun provideUserDao(database: AppDatabase): UserDao = database.getUserDao()

    private companion object {
        private const val DATABASE_NAME = "simple_posts_app_db"
    }
}