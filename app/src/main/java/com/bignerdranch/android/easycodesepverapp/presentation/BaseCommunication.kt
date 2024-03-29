package com.bignerdranch.android.easycodesepverapp.presentation

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer

class BaseCommunication: Communication {

    private val liveData = MutableLiveData<MainViewModel.State>()

    override fun showData(state: MainViewModel.State) {
        liveData.value = state
    }

    override fun observe(owner: LifecycleOwner, observer: Observer<MainViewModel.State>) {
        liveData.observe(owner,observer)
    }

    override fun isState(type: Int): Boolean {
        return liveData.value?.isType(type) ?: false
    }
}