package com.bignerdranch.android.easycodesepverapp.data


interface JokeDataFetcher {
    suspend fun getJoke(): JokeDataModel
}