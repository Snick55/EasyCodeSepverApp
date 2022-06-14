package com.bignerdranch.android.easycodesepverapp.data.cache

import com.bignerdranch.android.easycodesepverapp.domain.Joke

interface ChangeJokeStatus {

    suspend fun addOrRemove(id: Int, joke: Joke): Joke
}