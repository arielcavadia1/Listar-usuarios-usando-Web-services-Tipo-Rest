package co.com.ceiba.mobile.pruebadeingreso.aplication

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MainAplication  : Application(){

    override fun onCreate() {
        super.onCreate()
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        startKoin { androidContext(this@MainAplication)
            modules(getModules())}

    }
    private fun getModules() = arrayListOf(
        homeModule
    )
}