package com.bignerdranch.android.easycodesepverapp.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.bignerdranch.android.easycodesepverapp.JokeApp
import com.bignerdranch.android.easycodesepverapp.R


class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = (application as JokeApp).mainViewModel

        val button = findViewById<Button>(R.id.getJokeButton)
        val textView = findViewById<TextView>(R.id.textView)
        val progressBar = findViewById<ProgressBar>(R.id.progressBar)
        val checkBox = findViewById<CheckBox>(R.id.checkBox)
        val changeButton = findViewById<ImageButton>(R.id.changeButton)
        
        changeButton.setOnClickListener {
            viewModel.changeJokeStatus()
        }
        checkBox.setOnCheckedChangeListener { _, isChecked ->
             viewModel.chooseFavorites(isChecked)
        }

        progressBar.visibility = View.INVISIBLE

        button.setOnClickListener {

            viewModel.getJoke()
        }

        viewModel.observe(this){state->
            state.show(progressBar,button,textView,changeButton)
        }



    }




}