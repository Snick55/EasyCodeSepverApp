package com.bignerdranch.android.easycodesepverapp.data.realm

import com.bignerdranch.android.easycodesepverapp.core.Mapper
import com.bignerdranch.android.easycodesepverapp.data.JokeDataModel
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class JokeRealmModel: RealmObject(),Mapper<JokeDataModel> {

    @PrimaryKey
    var id: Int = -1
    var text: String? = ""
    var type: String = ""
    var punchline: String? = ""
    var joke: String = ""

    override fun to(): JokeDataModel  =  if (text != null && punchline != null)
        JokeDataModel (id,type, text, punchline, joke = "")
    else
        JokeDataModel(id, type, text= "", punchline= "", joke)



}