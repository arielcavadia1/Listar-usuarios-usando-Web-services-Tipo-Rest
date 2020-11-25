package co.com.ceiba.mobile.pruebadeingreso.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import co.com.ceiba.mobile.pruebadeingreso.base.BaseViewModel
import co.com.ceiba.mobile.pruebadeingreso.model.PostEntity
import co.com.ceiba.mobile.pruebadeingreso.room.RoomAppDb
import co.com.ceiba.mobile.pruebadeingreso.model.UserEntity

class PostActivityViewModel(app:Application) : BaseViewModel(app){
     val isLoading = MutableLiveData<Boolean>()

     val listPost = MutableLiveData<ArrayList<PostEntity>>()


    /**
     * Obtienes una lista de publicaciones, si no existen datos en la base de datos
     * obtiene la lista desde la web service
     */
    fun getListPost(user : UserEntity){
        isLoading.value = true
        val postDao = RoomAppDb.getAppDataBase((getApplication()))?.postsDao()
        val list = postDao?.getPosts(user.id)?: arrayListOf()

        if(list.isEmpty()){
            getPosts(user)
        }else{
            listPost.value = getPostList(user)
            isLoading.value = false
        }
    }

    fun getPostList(user: UserEntity): ArrayList<PostEntity>{
        val postDao = RoomAppDb.getAppDataBase((getApplication()))?.postsDao()
        val list = postDao?.getPosts(user.id)?: arrayListOf()
        return ArrayList<PostEntity>(list)
    }

    /**
     * Obtiene una lista de posts por usuario
     * @param user : usuario a consultar
     */
    private fun getPosts(user: UserEntity){
        isLoading.value = true
        network.connection(network.api.getPosts(user.id)){ listPosts, error ->
            if(error != null){
                return@connection
            }
            listPosts!!.forEach{insertPost(it)}

            listPost.value = listPosts
            isLoading.value = false
        }
    }

    /**
     * inserta una publicacion a la base datos
     */

    fun insertPost(postEntity: PostEntity):Boolean{
        val postDao = RoomAppDb.getAppDataBase(getApplication())?.postsDao()
        if(postDao != null){
            postDao.insertPost(postEntity)
            return true
        }
       return false
    }
}