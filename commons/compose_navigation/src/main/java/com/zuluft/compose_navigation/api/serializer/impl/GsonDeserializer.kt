package com.zuluft.compose_navigation.api.serializer.impl

import com.google.gson.Gson
import com.zuluft.compose_navigation.api.serializer.Deserializer

class GsonDeserializer(val gson: Gson) : Deserializer<String> {
    override fun <P> deserialize(arg: String, clazz: Class<P>): P {
        return gson.fromJson(arg, clazz)
    }

}