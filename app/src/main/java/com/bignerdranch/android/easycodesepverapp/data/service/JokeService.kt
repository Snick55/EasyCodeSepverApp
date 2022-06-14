package com.bignerdranch.android.easycodesepverapp.data.service


import retrofit2.Call
import retrofit2.http.GET


interface JokeService {
    @GET("https://v2.jokeapi.dev/joke/any")
    fun getJoke(): Call<JokeServerModel>
}


