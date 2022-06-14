package com.bignerdranch.android.easycodesepverapp

import androidx.annotation.DrawableRes

interface DataCallback {

    fun provideText(text: String)

    fun provideIconRes(@DrawableRes id: Int)

}