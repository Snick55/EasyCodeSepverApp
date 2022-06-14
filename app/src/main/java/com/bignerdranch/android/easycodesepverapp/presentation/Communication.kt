package com.bignerdranch.android.easycodesepverapp.presentation

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer

interface Communication {

    fun showData(state: MainViewModel.State)

    fun observe(owner: LifecycleOwner,observer: Observer<MainViewModel.State>)

    fun isState(type: Int): Boolean
}