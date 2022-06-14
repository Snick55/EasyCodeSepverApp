package com.bignerdranch.android.easycodesepverapp.data

import com.bignerdranch.android.easycodesepverapp.*
import com.bignerdranch.android.easycodesepverapp.data.cache.CacheDataSource
import com.bignerdranch.android.easycodesepverapp.data.cloud.CloudDataSource
import com.bignerdranch.android.easycodesepverapp.data.cache.CachedJoke
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception

//
class BaseJokeRepository(
    private val cloudDataSource: CloudDataSource,
    private val cacheDataSource: CacheDataSource,
    private val cachedJokes: CachedJoke
): JokeRepository {

    private var currentDataSource: JokeDataFetcher = cloudDataSource

    override suspend fun getJoke(): JokeDataModel = withContext(Dispatchers.IO)  {
        try {
            val joke = currentDataSource.getJoke()
            cachedJokes.saveJoke(joke)
            return@withContext joke
        }catch (e: Exception){
            cachedJokes.clear()
            throw e
        }
    }

    override suspend fun changeJokeStatus(): JokeDataModel? = cachedJokes.change(cacheDataSource)
    
    override fun chooseDataSource(cached: Boolean) { currentDataSource = if (cached)
        cacheDataSource
    else
        cloudDataSource
    }



//    private interface ResultHandler<S,E>{
//        fun handleResult(result: Result<S,E>): JokeUiModel
//    }
//
//    private abstract inner class BaseResultHandler<S,E>
//        (private val jokeDataFetcher: JokeDataFetcher<S, E>):ResultHandler<S,E>{
//
//            suspend fun process(): JokeUiModel{
//                return handleResult(jokeDataFetcher.getJoke())
//            }
//
//    }
//    private inner class CloudResultHandler(
//        jokeDataFetcher: JokeDataFetcher<JokeServerModel, ErrorType>,
//    ):BaseResultHandler<JokeServerModel,ErrorType>(jokeDataFetcher){
//        override fun handleResult(result: Result<JokeServerModel, ErrorType>): JokeUiModel = when(result){
//            is Result.Success<JokeServerModel> ->{
//                result.data.toJoke().let {
//                    cachedJokeServerModel = it
//                    it.toBaseJoke()
//                }
//            }
//            is Result.Error<ErrorType> -> {
//                cachedJokeServerModel = null
//                val errorType = if(result.exception ==  ErrorType.NO_CONNECTION)
//                    noConnection
//                else
//                    serviceUnavailable
//                FailureJokeUiModel(errorType.getMessage())
//            }
//
//        }
//    }
//
//    private inner class CachedResultHandler(
//        jokeDataFetcher: JokeDataFetcher<Joke, Unit>
//    ):BaseResultHandler<Joke,Unit>(jokeDataFetcher){
//        override fun handleResult(result: Result<Joke, Unit>): JokeUiModel = when(result){
//            is Result.Success<Joke> ->{
//                result.data.let {
//                    cachedJokeServerModel = it
//                    it.toFavoriteJoke()
//                }
//            }
//            is Result.Error<Unit> -> {
//                cachedJokeServerModel = null
//                FailureJokeUiModel(noCached.getMessage())
//            }
//
//        }
//    }


}