package com.alexxer.simplepostapplication.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.alexxer.simplepostapplication.data.db.DatabaseParams
import com.alexxer.simplepostapplication.data.db.entity.UserEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE, entity = UserEntity::class)
    suspend fun insertUsers(users: List<UserEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE, entity = UserEntity::class)
    suspend fun insertUser(user: UserEntity)

    @Query("SELECT * FROM ${DatabaseParams.USER_TABLE_NAME} ORDER BY ${DatabaseParams.ID}")
    fun getUsers(): Flow<List<UserEntity>>

    @Query("SELECT * FROM ${DatabaseParams.USER_TABLE_NAME} WHERE ${DatabaseParams.ID} = :userId")
    fun getUserById(userId: Int): Flow<UserEntity>
}