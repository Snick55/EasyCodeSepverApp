package com.bignerdranch.android.easycodesepverapp.data.service


import com.bignerdranch.android.easycodesepverapp.core.Mapper
import com.bignerdranch.android.easycodesepverapp.data.JokeDataModel
import com.google.gson.annotations.SerializedName

data class JokeServerModel(
    @SerializedName("id")
    private val id: Int,
    @SerializedName("type")
    private val type: String,
    @SerializedName("setup")
    private val text: String?,
    @SerializedName("delivery")
    private val punchline: String?,
    @SerializedName("joke")
    private val joke: String
): Mapper<JokeDataModel>{
   override fun to() = if(text != null && punchline != null)
        JokeDataModel(id, type, text, punchline, joke= "")
    else
        JokeDataModel(id, type, text=null, punchline=null, joke)
}
