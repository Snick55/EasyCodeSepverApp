package com.bignerdranch.android.easycodesepverapp.domain

interface JokeInteractor {

    suspend fun getJoke(): Joke

    suspend fun changeFavorite(): Joke

    suspend fun getFavoriteJokes(favorites: Boolean)

}