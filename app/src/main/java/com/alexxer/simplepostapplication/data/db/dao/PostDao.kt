package com.alexxer.simplepostapplication.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.alexxer.simplepostapplication.data.db.entity.PostEntity
import com.alexxer.simplepostapplication.data.db.entity.UserPostEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PostDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE, entity = PostEntity::class)
    suspend fun insertPosts(posts: List<PostEntity>)

    @Query(
        """
        SELECT users.name as name,
        posts.title as title
        FROM posts 
        JOIN users ON posts.user_id = users.id
        """
    )
    fun getPosts(): Flow<List<UserPostEntity>>
}
