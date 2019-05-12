package com.example.im_blog.http.gson

import com.example.im_blog.base.App
import com.google.gson.Gson
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import org.kodein.di.generic.instance
import java.lang.reflect.Type

class PicConverter:JsonDeserializer<String>{

    private val gson:Gson by App.INSTANCE.kodein.instance()

    override fun deserialize(json: JsonElement, typeOfT: Type, context: JsonDeserializationContext): String {
        val array = json.asJsonArray
        val new = array.asSequence()
            .map { it.asJsonObject }
            .map { it["thumbnail_pic"].asString }
            .toList()
        return gson.toJson(new)
    }
}