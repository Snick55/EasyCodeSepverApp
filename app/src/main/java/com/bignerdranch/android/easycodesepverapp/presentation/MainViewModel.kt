package com.bignerdranch.android.easycodesepverapp.presentation


import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.annotation.DrawableRes
import androidx.lifecycle.*
import com.bignerdranch.android.easycodesepverapp.domain.JokeInteractor
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class  MainViewModel(
    private val interactor: JokeInteractor,
    private val communication: Communication,
    private val dispatcher: CoroutineDispatcher = Dispatchers.Main
    ): ViewModel() {





    fun getJoke()= viewModelScope.launch(dispatcher) {
        communication.showData(State.Progress)
        interactor.getJoke().to().show(communication)
    }


    fun changeJokeStatus() =viewModelScope.launch(dispatcher) {
        if (communication.isState(State.INITIAL)){
            interactor.changeFavorite().to().show(communication)
        }
    }


    fun chooseFavorites(favorites: Boolean) =viewModelScope.launch {  interactor.getFavoriteJokes(favorites) }

    fun  observe(owner: LifecycleOwner, observer: Observer<State>){
        communication.observe(owner, observer)
    }


    sealed class State{

        protected abstract val type: Int

        companion object{
           const val INITIAL = 0
           const val PROGRESS = 1
           const val FAILED = 2
        }

        fun isType(type: Int): Boolean = this.type == type

        abstract fun show(
            progress: View,
            button: Button,
            textView: TextView,
            imageButton: ImageButton
        )

        object Progress: State(){
            override val type: Int = PROGRESS

            override fun show(
                progress: View,
                button: Button,
                textView: TextView,
                imageButton: ImageButton
            ) {
                progress.visibility = View.VISIBLE
                button.isEnabled = false
            }
        }
        abstract class Info(private val text: String,@DrawableRes private val id: Int): State(){
            override fun show(
                progress: View,
                button: Button,
                textView: TextView,
                imageButton: ImageButton
            ) {
                progress.visibility = View.INVISIBLE
                button.isEnabled = true
                textView.text = text
                imageButton.setImageResource(id)
            }
        }

         class Initial(private val text: String, @DrawableRes private val id: Int): Info(text, id){
             override val type: Int = INITIAL
        }

        class Failed(text: String,@DrawableRes id :Int): Info(text, id){
            override val type: Int = FAILED
        }

    }

}