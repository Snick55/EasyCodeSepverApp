package com.bignerdranch.android.easycodesepverapp.domain

import android.content.Context
import androidx.annotation.StringRes

interface ResourceManager {

    fun getString(@StringRes resId:Int):String

}

class BaseResourceManager(private val context: Context): ResourceManager {

    override fun getString(resId: Int): String = context.getString(resId)
}