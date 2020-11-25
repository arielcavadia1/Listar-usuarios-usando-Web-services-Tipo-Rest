package com.pruebadeingreso

import android.app.Application
import android.content.Context
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import co.com.ceiba.mobile.pruebadeingreso.model.PostEntity
import co.com.ceiba.mobile.pruebadeingreso.model.UserEntity
import co.com.ceiba.mobile.pruebadeingreso.viewmodel.MainActivityViewModel
import co.com.ceiba.mobile.pruebadeingreso.viewmodel.PostActivityViewModel
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith


/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {


    /**
     * Comprueba si se inicia correctamente la aplicacion
     */
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("co.com.ceiba.mobile.pruebadeingreso", appContext.packageName)
    }

    /**
     * Comprueba la insercion de un nuevo usuario a la base de datos
     * se debe ingresa con un id distinto a los que ya existen , dado que la clave id es primaria
     * si se coloca un id que ya existe emitira un error
     */
    @Test
    fun insetUserTest(){
        val context = ApplicationProvider.getApplicationContext<Context>()
        val user = UserEntity(1002, "userTest2", "user_test2", "user_test2@test.com", "987654320")
        val insert = MainActivityViewModel(context.applicationContext as Application).insertUserDb(user)
        assertEquals(true, insert)

    }
    /**
     * Obtiene una lista de usuarios
     */

    @Test
    fun getListUserTest(){
        val context = ApplicationProvider.getApplicationContext<Context>()
        val testUserList = MainActivityViewModel(context.applicationContext as Application).getUserList()
        assertTrue(testUserList.size > 0)
    }

    /**
     * Inserta una nueva publicacion a la base de datos al usuario con id = 1
     * se debe ingresa con un id distinto a los que ya existen , dado que la clave id es primaria
     * si se coloca un id que ya existe emitira un error
     */
    @Test
    fun insertPostTest(){
        val context = ApplicationProvider.getApplicationContext<Context>()
        val post = PostEntity(11,  1, "user_test2", "user_test2@test.com")
        val insert = PostActivityViewModel(context.applicationContext as Application).insertPost(post)
        assertEquals(true, insert)
    }
    /**
     * Obtiene la lista de publicaciones del usuario con id = 1
     */
    @Test
    fun getListPostTest(){
        val context = ApplicationProvider.getApplicationContext<Context>()
        val testPostList = PostActivityViewModel(context.applicationContext as Application).getPostList(UserEntity(1, "userTest", "user_test", "user_test@test.com", "987654321"))
        assertTrue(testPostList.size > 0)
    }

}