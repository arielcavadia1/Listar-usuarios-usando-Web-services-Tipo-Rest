package co.com.ceiba.mobile.pruebadeingreso.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import co.com.ceiba.mobile.pruebadeingreso.model.PostEntity
import co.com.ceiba.mobile.pruebadeingreso.model.UserEntity

@Database(entities = [UserEntity::class, PostEntity::class],version = 1)
abstract class RoomAppDb : RoomDatabase(){

    abstract fun userDao(): UserDao?
    abstract fun postsDao(): PostDao?
    companion object{

        private var INTANCE: RoomAppDb?= null

        fun getAppDataBase(context: Context): RoomAppDb? {
            if(INTANCE == null){
                INTANCE = Room.databaseBuilder<RoomAppDb>(
                    context.applicationContext, RoomAppDb::class.java, "AppDB"
                ).allowMainThreadQueries()
                    .build()
            }
            return INTANCE
        }
    }
}