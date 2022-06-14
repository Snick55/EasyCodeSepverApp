package com.bignerdranch.android.easycodesepverapp.domain

import com.bignerdranch.android.easycodesepverapp.*
import com.bignerdranch.android.easycodesepverapp.data.JokeRepository
import java.lang.Exception

class BaseJokeInteractor(
    private val repository: JokeRepository,
    private val jokeFailureHandler: JokeFailureHandler
    ): JokeInteractor {

    override suspend fun getJoke(): Joke {
      return try {
          Joke.Success(repository.getJoke().text,repository.getJoke().punchline,repository.getJoke().joke,false)
      }catch (e: Exception){
          Joke.Failed(jokeFailureHandler.handle(e))
      }
    }

    override suspend fun changeFavorite(): Joke {
        return try {
            val joke = repository.changeJokeStatus()
            Joke.Success(joke.text,joke.punchline,joke.joke)
        }catch (e: Exception){
            Joke.Failed(jokeFailureHandler.handle(e))
        }
    }

    override suspend fun getFavoriteJokes(favorites: Boolean) {
        repository.chooseDataSource(favorites)
    }
}


