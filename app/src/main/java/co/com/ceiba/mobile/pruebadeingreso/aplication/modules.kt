package co.com.ceiba.mobile.pruebadeingreso.aplication

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import co.com.ceiba.mobile.pruebadeingreso.view.adapters.ListUsersAdapter
import co.com.ceiba.mobile.pruebadeingreso.view.adapters.PostUserAdapter
import co.com.ceiba.mobile.pruebadeingreso.viewmodel.MainActivityViewModel
import co.com.ceiba.mobile.pruebadeingreso.viewmodel.PostActivityViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

private const val PREFERENCES_FILE_KEY = "com.example.settings_preferences"

/*
* set modules for inject
* */
val homeModule = module {
    viewModel { MainActivityViewModel(androidApplication()) }
    viewModel { PostActivityViewModel(androidApplication()) }
    single { ListUsersAdapter() }
    single { PostUserAdapter() }
    single{ provideSharedPref(androidApplication()) }
}

fun provideSharedPref(app: Application): SharedPreferences {
    return app.applicationContext.getSharedPreferences(
        PREFERENCES_FILE_KEY,
        Context.MODE_PRIVATE
    )
}


