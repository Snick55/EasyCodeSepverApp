package com.bignerdranch.android.easycodesepverapp

import android.app.Application
import com.bignerdranch.android.easycodesepverapp.data.cache.BaseCachedDataSource
import com.bignerdranch.android.easycodesepverapp.data.cloud.BaseCloudDataSource
import com.bignerdranch.android.easycodesepverapp.data.BaseJokeRepository
import com.bignerdranch.android.easycodesepverapp.data.realm.BaseRealmProvider
import com.bignerdranch.android.easycodesepverapp.data.service.JokeService
import com.bignerdranch.android.easycodesepverapp.domain.BaseResourceManager
import com.bignerdranch.android.easycodesepverapp.presentation.BaseCommunication
import com.bignerdranch.android.easycodesepverapp.presentation.MainViewModel
import io.realm.Realm
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class JokeApp: Application() {

    lateinit var mainViewModel: MainViewModel


    override fun onCreate() {
        super.onCreate()

        Realm.init(this)
        val retrofit = Retrofit.Builder()
            .baseUrl("https://www.google.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        mainViewModel = MainViewModel(
           BaseJokeRepository(
               BaseCloudDataSource(retrofit.create(JokeService::class.java)),
               BaseCachedDataSource(BaseRealmProvider()),
               BaseResourceManager(this)
           ),
            BaseCommunication()
        )
    }




}