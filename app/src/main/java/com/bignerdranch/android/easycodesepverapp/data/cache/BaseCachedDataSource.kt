package com.bignerdranch.android.easycodesepverapp.data.cache

import com.bignerdranch.android.easycodesepverapp.data.JokeDataModel
import com.bignerdranch.android.easycodesepverapp.domain.Joke
import com.bignerdranch.android.easycodesepverapp.presentation.JokeUiModel
import com.bignerdranch.android.easycodesepverapp.data.realm.JokeRealmModel
import com.bignerdranch.android.easycodesepverapp.data.realm.RealmProvider
import com.bignerdranch.android.easycodesepverapp.domain.NoCachedJokesExceptions
import io.realm.Realm
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class BaseCachedDataSource(private val realmProvider: RealmProvider): CacheDataSource {

    override suspend fun getJoke(): JokeDataModel  {
         realmProvider.provide().use {
            val jokes = it.where(JokeRealmModel::class.java).findAll()
            if (jokes.isEmpty()) {
                throw NoCachedJokesExceptions()
            } else {
               return jokes.random().to()
            }
        }
    }

    override suspend fun addOrRemove(id: Int, joke: Joke): JokeUiModel = withContext(Dispatchers.IO){
        Realm.getDefaultInstance().use {
            val jokeRealm =
                it.where(JokeRealmModel::class.java).equalTo("id",id).findFirst()
          return@withContext  if (jokeRealm == null){
              it.executeTransaction {transaction->
                  val newJoke = joke.to()
                  transaction.insert(newJoke)
              }
              joke.toFavoriteJoke()
            }else{
                it.executeTransaction{
                    jokeRealm.deleteFromRealm()
                }
              joke.toBaseJoke()
            }
        }
    }
}