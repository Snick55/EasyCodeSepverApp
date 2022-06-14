package com.bignerdranch.android.easycodesepverapp.data

interface JokeRepository {

   suspend fun getJoke(): JokeDataModel

    suspend fun changeJokeStatus(): JokeDataModel?

    fun chooseDataSource(cached: Boolean)
    
}



