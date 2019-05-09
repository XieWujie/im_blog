package com.example.im_blog.http.gson

import com.google.gson.Gson
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type
import java.text.SimpleDateFormat
import java.util.*

class PicConverter:JsonDeserializer<String>{

    override fun deserialize(json: JsonElement, typeOfT: Type, context: JsonDeserializationContext): String {
        val array = json.asJsonArray
        val new = array.asSequence()
            .map { it.asJsonObject }
            .map { it["thumbnail_pic"].asString }
            .toList()
        return Gson().toJson(new)
    }
}