package com.bignerdranch.android.easycodesepverapp.domain


import com.bignerdranch.android.easycodesepverapp.core.Mapper
import com.bignerdranch.android.easycodesepverapp.presentation.BaseJokeUiModel
import com.bignerdranch.android.easycodesepverapp.presentation.FailureJokeUiModel
import com.bignerdranch.android.easycodesepverapp.presentation.FavoriteJokeUiModel
import com.bignerdranch.android.easycodesepverapp.presentation.JokeUiModel


sealed class Joke: Mapper<JokeUiModel> {

    class Success(
        private val text: String?,
        private val punchline: String?,
        private val joke: String,
        private val favorite: Boolean
    ): Joke(){
        override fun to(): JokeUiModel {
            return if (favorite) {
                if (text != null && punchline != null)
                    FavoriteJokeUiModel (text, punchline)
                else
                    FavoriteJokeUiModel("", joke)

            }else{
                if (text != null && punchline != null)
                    BaseJokeUiModel (text, punchline)
                else
                    BaseJokeUiModel("", joke)
            }
        }
    }


    class Failed(private val failure: JokeFailure): Joke() {
        override fun to(): JokeUiModel {
            return FailureJokeUiModel(failure.getMessage())
        }
    }
}

