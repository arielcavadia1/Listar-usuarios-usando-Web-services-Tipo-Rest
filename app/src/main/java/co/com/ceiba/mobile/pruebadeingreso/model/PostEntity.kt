package co.com.ceiba.mobile.pruebadeingreso.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "post")
class PostEntity(
    @PrimaryKey(autoGenerate = false) @ColumnInfo(name = "id") val id : Int = 0,
    @ColumnInfo(name = "userId") val userId : Int,
    @ColumnInfo(name = "title") val title : String,
    @ColumnInfo(name = "body")   val body:String
) :Serializable