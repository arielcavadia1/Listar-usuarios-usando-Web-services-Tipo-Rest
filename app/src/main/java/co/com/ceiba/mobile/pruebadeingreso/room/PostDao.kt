package co.com.ceiba.mobile.pruebadeingreso.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import co.com.ceiba.mobile.pruebadeingreso.model.PostEntity

@Dao
interface PostDao {

    @Query("SELECT * FROM post WHERE userId == :userId")
    fun getPosts(userId:Int): List<PostEntity>

    @Insert
    fun insertPost(post: PostEntity)
}