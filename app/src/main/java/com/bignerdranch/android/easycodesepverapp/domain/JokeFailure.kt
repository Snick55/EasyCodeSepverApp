package com.bignerdranch.android.easycodesepverapp.domain

import com.bignerdranch.android.easycodesepverapp.R

interface JokeFailure {
    fun getMessage():String
}

class NoCachedJokes(private val baseResourceManager: ResourceManager): JokeFailure {
    override fun getMessage(): String = baseResourceManager.getString(R.string.no_cached_jokes)
}

class NoConnection(private val baseResourceManager: ResourceManager): JokeFailure {
    override fun getMessage(): String = baseResourceManager.getString(R.string.no_connection)
}

class ServiceUnavailable(private val baseResourceManager: ResourceManager): JokeFailure {
    override fun getMessage(): String = baseResourceManager.getString(R.string.service_unavailable)
}

class GenericError(private val baseResourceManager: ResourceManager): JokeFailure{
    override fun getMessage(): String = baseResourceManager.getString(R.string.generic_fail_message)
}