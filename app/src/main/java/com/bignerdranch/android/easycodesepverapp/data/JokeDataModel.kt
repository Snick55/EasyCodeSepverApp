package com.bignerdranch.android.easycodesepverapp.data

import com.bignerdranch.android.easycodesepverapp.data.cache.ChangeJokeStatus
import com.bignerdranch.android.easycodesepverapp.domain.Joke

class JokeDataModel(
    private val id: Int,
    private val type: String,
    val text: String?,
    val punchline: String?,
    val joke: String,
    val cached: Boolean = false
): ChangeJokeStatus {
   overr suspend fun change(changeJokeStatus: ChangeJokeStatus): JokeDataModel = changeJokeStatus.addOrRemove(id, Joke(text,punchline,joke,cached))
}