package co.com.ceiba.mobile.pruebadeingreso.network

import co.com.ceiba.mobile.pruebadeingreso.model.PostEntity
import co.com.ceiba.mobile.pruebadeingreso.model.UserEntity
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitApi {
    @GET("/users")
    fun getUsers() : Observable<ArrayList<UserEntity>>

    @GET("/posts")
    fun getPosts(@Query("userId") line : Int ) : Observable<ArrayList<PostEntity>>
}