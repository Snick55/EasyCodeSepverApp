package com.bignerdranch.android.easycodesepverapp.data.cloud

import com.bignerdranch.android.easycodesepverapp.data.JokeDataModel
import com.bignerdranch.android.easycodesepverapp.data.service.JokeService
import com.bignerdranch.android.easycodesepverapp.domain.NoConnectionException
import com.bignerdranch.android.easycodesepverapp.domain.ServiceUnavailableException
import java.lang.Exception
import java.net.UnknownHostException

class BaseCloudDataSource(private val service: JokeService): CloudDataSource {

    override suspend fun getJoke():JokeDataModel {
         try {
            return  service.getJoke().execute().body()!!.to()
        }catch (e: Exception){
             if (e is UnknownHostException) {
                 throw NoConnectionException()
             }
            else {
                 throw ServiceUnavailableException()
             }
        }
    }
}