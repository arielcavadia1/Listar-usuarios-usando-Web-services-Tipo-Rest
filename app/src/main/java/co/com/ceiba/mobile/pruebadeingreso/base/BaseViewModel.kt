package co.com.ceiba.mobile.pruebadeingreso.base

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import co.com.ceiba.mobile.pruebadeingreso.network.RetrofitConnection
import org.koin.core.KoinComponent
// Base para los viewmodel , contiene los elementos que estos utilizaran
open class BaseViewModel(app: Application) : AndroidViewModel(app), KoinComponent {
    val network = RetrofitConnection()
}