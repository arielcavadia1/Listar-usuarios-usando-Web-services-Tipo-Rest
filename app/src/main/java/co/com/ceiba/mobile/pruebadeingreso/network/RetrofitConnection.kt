package co.com.ceiba.mobile.pruebadeingreso.network

import android.util.Log
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class RetrofitConnection {

    private lateinit var disposable : Disposable
    var api : RetrofitApi = RetrofitClient().getInstance().create(RetrofitApi::class.java)

    /**
     * Function Connect to web service
     * @param method : method to web service
     * @param callback : callback in response for callback
     */

    fun<T> connection(method: Observable<T>, callback: (success:T?, error: String?)->Unit){

        disposable = method.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Log.d("Conexion","success $it")
                callback.invoke(it, null)
            },{
                Log.d("conexion", "error ${it.cause} ${it.message}")
                callback.invoke(null, it.cause.toString())
            })
    }
}