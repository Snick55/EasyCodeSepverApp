package com.bignerdranch.android.easycodesepverapp.domain

import com.bignerdranch.android.easycodesepverapp.R
import java.lang.Exception

interface JokeFailureHandler {
    fun handle(e: Exception): JokeFailure
}

class JokeFailureFactory(private val resourceManager: ResourceManager): JokeFailureHandler{
    override fun handle(e: Exception): JokeFailure {
       return when(e){
           is NoConnectionException -> NoConnection(resourceManager)
           is ServiceUnavailableException -> ServiceUnavailable(resourceManager)
           is NoCachedJokesExceptions -> NoCachedJokes(resourceManager)
           else -> GenericError(resourceManager)
       }
    }
}