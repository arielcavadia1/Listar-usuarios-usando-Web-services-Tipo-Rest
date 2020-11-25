package co.com.ceiba.mobile.pruebadeingreso.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import co.com.ceiba.mobile.pruebadeingreso.model.UserEntity

@Dao
interface UserDao {

    @Query("SELECT * FROM user")
    fun getUsers(): List<UserEntity>?

    @Insert
    fun insertUser(user: UserEntity?)

}