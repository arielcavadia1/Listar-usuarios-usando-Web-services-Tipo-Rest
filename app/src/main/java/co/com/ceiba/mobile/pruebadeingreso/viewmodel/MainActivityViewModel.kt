package co.com.ceiba.mobile.pruebadeingreso.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import co.com.ceiba.mobile.pruebadeingreso.base.BaseViewModel
import co.com.ceiba.mobile.pruebadeingreso.room.RoomAppDb
import co.com.ceiba.mobile.pruebadeingreso.model.UserEntity
import java.util.*
import kotlin.collections.ArrayList

class MainActivityViewModel(app : Application) : BaseViewModel(app){
     val isLoading = MutableLiveData<Boolean>()

     var listUsers = MutableLiveData<ArrayList<UserEntity>>()

     val filterUsers = MutableLiveData<ArrayList<UserEntity>>()

     val emptyList = MutableLiveData<Boolean>()

     val error = MutableLiveData<String>()

    /**
     * Obtiene una lista de usuarios, si no existen datos en la base de datos
     * obtiene la lista desde la web service
     */
    fun getListUsers(){
        isLoading.value = true

        val userDao  = RoomAppDb.getAppDataBase((getApplication()))?.userDao()
        val list = userDao?.getUsers()?: arrayListOf()

        if(list.isEmpty()){
            getUsers()
        }else{
            emptyList.value = false
            listUsers.value =  getUserList()
            isLoading.value = false
        }
    }

    fun getUserList() : ArrayList<UserEntity>{
        val userDao  = RoomAppDb.getAppDataBase((getApplication()))?.userDao()
        val list = userDao?.getUsers()?: arrayListOf()
        return  ArrayList<UserEntity>(list)
    }


    /**
     * Obtiene una lista de usuarios disponibles desde la web service
     */
    private fun getUsers(){
        isLoading.value = true
        network.connection(network.api.getUsers()){ listUser, error ->
            if (error != null) {
                this.error.value = error
                return@connection
            }

            listUser!!.forEach {
                insertUserDb(it)
            }

            emptyList.value = listUser.isEmpty()
            if (!listUser.isEmpty()) listUsers.value = listUser
            isLoading.value = false

        }

    }


    /**
     * Obtiene una lista en base a una cadena
     */
    fun getSearch( string : String){
        val data = listUsers.value!!.filter { it.username.toLowerCase(Locale.ROOT).contains(string.toLowerCase().trim())}
        emptyList.value = data.isEmpty()
        filterUsers.value = ArrayList(data)

    }

    /**
     * inserta un usuario a la base de datos
     */
     fun insertUserDb(entity: UserEntity) : Boolean{
        val userDao = RoomAppDb.getAppDataBase(getApplication())?.userDao()
        if (userDao != null){
            userDao.insertUser(entity)
            return true
        }
        return false
    }
}
