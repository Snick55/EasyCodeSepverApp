package com.bignerdranch.android.easycodesepverapp.presentation

import androidx.annotation.DrawableRes
import com.bignerdranch.android.easycodesepverapp.R
import com.bignerdranch.android.easycodesepverapp.presentation.MainViewModel.State


class BaseJokeUiModel(text: String, punchline: String): JokeUiModel(text, punchline){
    override fun getIconResById(): Int = R.drawable.ic_baseline_favorite_border_24
}

class FavoriteJokeUiModel(text: String, punchline: String): JokeUiModel(text, punchline){
    override fun getIconResById(): Int = R.drawable.ic_baseline_favorite_24
}

class FailureJokeUiModel(text: String): JokeUiModel(text, ""){
    override fun getIconResById(): Int = 0
    override fun show(communication: Communication) {
        communication.showData(State.Failed(text(),getIconResById()))
    }
}


abstract class JokeUiModel(private val text: String, private val punchline: String) {

   protected fun text() = "$text\n$punchline "

    @DrawableRes
    protected abstract fun getIconResById(): Int

   open fun show(communication: Communication) = communication.showData(State.Initial(text(),getIconResById()))



}