package com.bignerdranch.android.easycodesepverapp.data.cache

import com.bignerdranch.android.easycodesepverapp.data.JokeDataModel

interface CachedJoke {

    fun saveJoke(joke: JokeDataModel)

    fun clear()

    suspend fun change(changeJokeStatus: ChangeJokeStatus): JokeDataModel?

}

class BaseCachedJoke: CachedJoke{

    private var cached: JokeDataModel? = null


    override fun saveJoke(joke: JokeDataModel) {
        cached = joke
    }

    override fun clear() {
        cached = null
    }

    override suspend fun change(changeJokeStatus: ChangeJokeStatus): JokeDataModel? {
       return cached?.change(changeJokeStatus)
    }
}