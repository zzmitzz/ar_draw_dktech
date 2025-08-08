package com.dktech.baseandroidviewdktech

import android.app.Application
import android.content.Context
import androidx.datastore.preferences.preferencesDataStore
import com.dktech.baseandroidviewdktech.utils.PersistentStorage
import dagger.hilt.android.HiltAndroidApp

val Context.appDataStore by preferencesDataStore(
    name = PersistentStorage.Key.PREFERENCE_KEY
)

@HiltAndroidApp
class MyApplication: Application() {

}