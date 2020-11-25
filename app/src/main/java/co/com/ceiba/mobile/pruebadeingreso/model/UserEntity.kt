package co.com.ceiba.mobile.pruebadeingreso.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "user")
class UserEntity(
    @PrimaryKey(autoGenerate = false) @ColumnInfo(name= "id") val id:Int = 0,
    @ColumnInfo(name = "name") val name:String,
    @ColumnInfo(name = "username") val username:String,
    @ColumnInfo(name = "email") val email : String,
    @ColumnInfo(name = "phone") val phone : String
):Serializable
